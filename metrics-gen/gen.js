import { faker } from '@faker-js/faker';
import { MongoClient } from 'mongodb';

function randomIntFromInterval(min, max) {
    return Math.floor(Math.random() * (max - min + 1) + min);
}

async function seedDB() {

    const uri = "mongodb://mongo:mongo@localhost:37017/metrics";

    const client = new MongoClient(uri, {
        useNewUrlParser: true,
        // useUnifiedTopology: true,
    });

    try {
        await client.connect();
        console.log("Connected correctly to server");

        const collection = client.db("metrics").collection("metrics-0");

        // await collection.drop();

        let timeSeriesData = [];

        for (let i = 0; i < 5000; i++) {
            let metric = {
                timestamp: faker.date.past(),
                metric: faker.random.word(),
                detail: {
                    deviceId: faker.datatype.uuid(),
                    sectorId: faker.datatype.uuid()
                },
                events: [],
            };

            for (let j = 0; j < randomIntFromInterval(1, 6); j++) {
                let newEvent = {
                    timestamp: faker.date.past(),
                    value: randomIntFromInterval(14,16),
                }
                metric.events.push(newEvent);
            }
            timeSeriesData.push(metric);
        }
        await collection.insertMany(timeSeriesData);

        console.log("Database seeded!");
        await client.close();
    } catch (err) {
        console.log(err.stack);
    }
}

seedDB();