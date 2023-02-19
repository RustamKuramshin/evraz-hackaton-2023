/// Нужно будет парсить значение, получаемое по ключу в float
// Spring bot стандартный и jdk 16
import React, { useEffect, useRef, useState } from 'react';
import { Route, Switch } from 'react-router-dom';
import Header from './Header';
import MainPage from './MainPage';
import ExgausterScreen from './ExgausterScreen';
const { dataForExg } = require('../utils/ExgaustersData');

function App() {
  const [data, setData] = useState({});

  function getKeyByValue(object, value) {
    return Object.keys(object).find(key => object[key] === value);
  }

  const oilImg = (oilKey) => {
    if(data[oilKey] < 20 && data[oilKey] > 10) {
      return 'bearing__liquid_warning';
    } else if (data[oilKey] < 10)
      return 'bearing__liquid_alarm';
    else {
      console.log("aboba")
      return ''
    }
  }

  const temperatureImg = (tempKey) => {
    if(data[tempKey] >= 65 && JSON.stringify(data)[tempKey] < 75)
      return `bearing__temperature_warning`;
    else if (data[tempKey] >= 75)
      return `bearing__temperature_alarm`;
    else {
      console.log("T aboba")
      return ''
    }
  }

  const [oilFirstModify, setOilFirstModify] = useState('');
  const [oilSecondModify, setOilSecondModify] = useState('');
  const [oilThirdModify, setOilThirdModify] = useState('');
  const [oilFourthModify, setOilFourthModify] = useState('');

  const [exgOneTempr, setExgOneTemr] = useState([]);


  const [boolCheck, setBoolCheck] = useState(false);
  const [oilModify, setOilModify] = useState('');
  const [paginationName, setPaginationName] = useState('Прогнозная аналитика эксгаустеров');
  const socket = useRef(new WebSocket('ws://51.250.23.216:38080/metrics'));

  //console.log([input, data]);

  const oilOne = dataForExg["Эксгаустер У-171"]["Маслосистема"].oil_level;
  const oilTwo = dataForExg["Эксгаустер У-172"]["Маслосистема"].oil_level;
  const oilThree = dataForExg["Эксгаустер Ф-171"]["Маслосистема"].oil_level;
  const oilFour = dataForExg["Эксгаустер Ф-172"]["Маслосистема"].oil_level;
  //const oilFive = dataForExg["Эксгаустер Х-171"]["Маслосистема"].oil_level;
  //const oilSix = dataForExg["Эксгаустер X-172"]["Маслосистема"].oil_level;


  const ExgOnetemperatureOne = dataForExg["Эксгаустер У-171"][`Подшипник[1]`].temperature.standart;
  const ExgOnetemperatureTwo = dataForExg["Эксгаустер У-171"][`Подшипник[2]`].temperature.standart;
  const ExgOnetemperatureThree = dataForExg["Эксгаустер У-171"][`Подшипник[3]`].temperature.standart;
  const ExgOnetemperatureFour = dataForExg["Эксгаустер У-171"][`Подшипник[4]`].temperature.standart;
  const ExgOnetemperatureFive = dataForExg["Эксгаустер У-171"][`Подшипник[5]`].temperature.standart;
  const ExgOnetemperatureSix = dataForExg["Эксгаустер У-171"][`Подшипник[6]`].temperature.standart;
  const ExgOnetemperatureSeven = dataForExg["Эксгаустер У-171"][`Подшипник[7]`].temperature.standart;
  const ExgOnetemperatureEight = dataForExg["Эксгаустер У-171"][`Подшипник[8]`].temperature.standart;
  const ExgOnetemperatureNine = dataForExg["Эксгаустер У-171"][`Подшипник[9]`].temperature.standart;

  const ExgTwotemperatureOne = dataForExg["Эксгаустер У-172"][`Подшипник[1]`].temperature.standart;
  const ExgTwotemperatureTwo = dataForExg["Эксгаустер У-172"][`Подшипник[2]`].temperature.standart;
  const ExgTwotemperatureThree = dataForExg["Эксгаустер У-172"][`Подшипник[3]`].temperature.standart;
  const ExgTwotemperatureFour = dataForExg["Эксгаустер У-172"][`Подшипник[4]`].temperature.standart;
  const ExgTwotemperatureFive = dataForExg["Эксгаустер У-172"][`Подшипник[5]`].temperature.standart;
  const ExgTwotemperatureSix = dataForExg["Эксгаустер У-172"][`Подшипник[6]`].temperature.standart;
  const ExgTwotemperatureSeven = dataForExg["Эксгаустер У-172"][`Подшипник[7]`].temperature.standart;
  const ExgTwotemperatureEight = dataForExg["Эксгаустер У-172"][`Подшипник[8]`].temperature.standart;
  const ExgTwotemperatureNine = dataForExg["Эксгаустер У-172"][`Подшипник[9]`].temperature.standart;

  const ExgThreetemperatureOne = dataForExg["Эксгаустер Ф-171"][`Подшипник[1]`].temperature.standart;
  const ExgThreetemperatureTwo = dataForExg["Эксгаустер Ф-171"][`Подшипник[2]`].temperature.standart;
  const ExgThreetemperatureThree = dataForExg["Эксгаустер Ф-171"][`Подшипник[3]`].temperature.standart;
  const ExgThreetemperatureFour = dataForExg["Эксгаустер Ф-171"][`Подшипник[4]`].temperature.standart;
  const ExgThreetemperatureFive = dataForExg["Эксгаустер Ф-171"][`Подшипник[5]`].temperature.standart;
  const ExgThreetemperatureSix = dataForExg["Эксгаустер Ф-171"][`Подшипник[6]`].temperature.standart;
  const ExgThreetemperatureSeven = dataForExg["Эксгаустер Ф-171"][`Подшипник[7]`].temperature.standart;
  const ExgThreetemperatureEight = dataForExg["Эксгаустер Ф-171"][`Подшипник[8]`].temperature.standart;
  const ExgThreetemperatureNine = dataForExg["Эксгаустер Ф-171"][`Подшипник[9]`].temperature.standart;

  const ExgFourtemperatureOne = dataForExg["Эксгаустер Ф-172"][`Подшипник[1]`].temperature.standart;
  const ExgFourtemperatureTwo = dataForExg["Эксгаустер Ф-172"][`Подшипник[2]`].temperature.standart;
  const ExgFourtemperatureThree = dataForExg["Эксгаустер Ф-172"][`Подшипник[3]`].temperature.standart;
  const ExgFourtemperatureFour = dataForExg["Эксгаустер Ф-172"][`Подшипник[4]`].temperature.standart;
  const ExgFourtemperatureFive = dataForExg["Эксгаустер Ф-172"][`Подшипник[5]`].temperature.standart;
  const ExgFourtemperatureSix = dataForExg["Эксгаустер Ф-172"][`Подшипник[6]`].temperature.standart;
  const ExgFourtemperatureSeven = dataForExg["Эксгаустер Ф-172"][`Подшипник[7]`].temperature.standart;
  const ExgFourtemperatureEight = dataForExg["Эксгаустер Ф-172"][`Подшипник[8]`].temperature.standart;
  const ExgFourtemperatureNine = dataForExg["Эксгаустер Ф-172"][`Подшипник[9]`].temperature.standart;

  const ExgFivetemperatureOne = dataForExg["Эксгаустер Х-171"][`Подшипник[1]`].temperature.standart;
  const ExgFivetemperatureTwo = dataForExg["Эксгаустер Х-171"][`Подшипник[2]`].temperature.standart;
  const ExgFivetemperatureThree = dataForExg["Эксгаустер Х-171"][`Подшипник[3]`].temperature.standart;
  const ExgFivetemperatureFour = dataForExg["Эксгаустер Х-171"][`Подшипник[4]`].temperature.standart;
  const ExgFivetemperatureFive = dataForExg["Эксгаустер Х-171"][`Подшипник[5]`].temperature.standart;
  const ExgFivetemperatureSix = dataForExg["Эксгаустер Х-171"][`Подшипник[6]`].temperature.standart;
  const ExgFivetemperatureSeven = dataForExg["Эксгаустер Х-171"][`Подшипник[7]`].temperature.standart;
  const ExgFivetemperatureEight = dataForExg["Эксгаустер Х-171"][`Подшипник[8]`].temperature.standart;
  const ExgFivetemperatureNine = dataForExg["Эксгаустер Х-171"][`Подшипник[9]`].temperature.standart;

  // const ExgSixtemperatureOne = dataForExg["Эксгаустер Х-172"][`Подшипник[1]`].temperature.standart;
  // const ExgSixtemperatureTwo = dataForExg["Эксгаустер Х-172"][`Подшипник[2]`].temperature.standart;
  // const ExgSixtemperatureThree = dataForExg["Эксгаустер Х-172"][`Подшипник[3]`].temperature.standart;
  // const ExgSixtemperatureFour = dataForExg["Эксгаустер Х-172"][`Подшипник[4]`].temperature.standart;
  // const ExgSixtemperatureFive = dataForExg["Эксгаустер Х-172"][`Подшипник[5]`].temperature.standart;
  // const ExgSixtemperatureSix = dataForExg["Эксгаустер Х-172"][`Подшипник[6]`].temperature.standart;
  // const ExgSixtemperatureSeven = dataForExg["Эксгаустер Х-172"][`Подшипник[7]`].temperature.standart;
  // const ExgSixtemperatureEight = dataForExg["Эксгаустер Х-172"][`Подшипник[8]`].temperature.standart;
  // const ExgSixtemperatureNine = dataForExg["Эксгаустер Х-172"][`Подшипник[9]`].temperature.standart;


  const ExgOne_vibrationOneAx = dataForExg["Эксгаустер У-171"]['Подшипник[1]'].vibration_axial.standart;
  const ExgOne_vibrationOneHor = dataForExg["Эксгаустер У-171"]['Подшипник[1]'].vibration_horizontal.standart;
  const ExgOne_vibrationOneVer = dataForExg["Эксгаустер У-171"]['Подшипник[1]'].vibration_vertical.standart;

  const ExgOne_vibrationTwoAx = dataForExg["Эксгаустер У-171"]['Подшипник[2]'].vibration_axial.standart;
  const ExgOne_vibrationTwoHor = dataForExg["Эксгаустер У-171"]['Подшипник[2]'].vibration_horizontal.standart;
  const ExgOne_vibrationTwoVer = dataForExg["Эксгаустер У-171"]['Подшипник[2]'].vibration_vertical.standart;

  const ExgOne_vibrationSevenAx = dataForExg["Эксгаустер У-171"]['Подшипник[7]'].vibration_axial.standart;
  const ExgOne_vibrationSevenHor = dataForExg["Эксгаустер У-171"]['Подшипник[7]'].vibration_horizontal.standart;
  const ExgOne_vibrationSevenVer = dataForExg["Эксгаустер У-171"]['Подшипник[7]'].vibration_vertical.standart;

  const ExgOne_vibrationEightAx = dataForExg["Эксгаустер У-171"]['Подшипник[8]'].vibration_axial.standart;
  const ExgOne_vibrationEightHor = dataForExg["Эксгаустер У-171"]['Подшипник[8]'].vibration_horizontal.standart;
  const ExgOne_vibrationEightVer = dataForExg["Эксгаустер У-171"]['Подшипник[8]'].vibration_vertical.standart;


  const ExgTwo_vibrationOneAx = dataForExg["Эксгаустер У-172"]['Подшипник[1]'].vibration_axial.standart;
  const ExgTwo_vibrationOneHor = dataForExg["Эксгаустер У-172"]['Подшипник[1]'].vibration_horizontal.standart;
  const ExgTwo_vibrationOneVer = dataForExg["Эксгаустер У-172"]['Подшипник[1]'].vibration_vertical.standart;

  const ExgTwo_vibrationTwoAx = dataForExg["Эксгаустер У-172"]['Подшипник[2]'].vibration_axial.standart;
  const ExgTwo_vibrationTwoHor = dataForExg["Эксгаустер У-172"]['Подшипник[2]'].vibration_horizontal.standart;
  const ExgTwo_vibrationTwoVer = dataForExg["Эксгаустер У-172"]['Подшипник[2]'].vibration_vertical.standart;

  const ExgTwo_vibrationSevenAx = dataForExg["Эксгаустер У-172"]['Подшипник[7]'].vibration_axial.standart;
  const ExgTwo_vibrationSevenHor = dataForExg["Эксгаустер У-172"]['Подшипник[7]'].vibration_horizontal.standart;
  const ExgTwo_vibrationSevenVer = dataForExg["Эксгаустер У-172"]['Подшипник[7]'].vibration_vertical.standart;

  const ExgTwo_vibrationEightAx = dataForExg["Эксгаустер У-172"]['Подшипник[8]'].vibration_axial.standart;
  const ExgTwo_vibrationEightHor = dataForExg["Эксгаустер У-172"]['Подшипник[8]'].vibration_horizontal.standart;
  const ExgTwo_vibrationEightVer = dataForExg["Эксгаустер У-172"]['Подшипник[8]'].vibration_vertical.standart;


  const ExgThree_vibrationOneAx = dataForExg["Эксгаустер Ф-171"]['Подшипник[1]'].vibration_axial.standart;
  const ExgThree_vibrationOneHor = dataForExg["Эксгаустер Ф-171"]['Подшипник[1]'].vibration_horizontal.standart;
  const ExgThree_vibrationOneVer = dataForExg["Эксгаустер Ф-171"]['Подшипник[1]'].vibration_vertical.standart;

  const ExgThree_vibrationTwoAx = dataForExg["Эксгаустер Ф-171"]['Подшипник[2]'].vibration_axial.standart;
  const ExgThree_vibrationTwoHor = dataForExg["Эксгаустер Ф-171"]['Подшипник[2]'].vibration_horizontal.standart;
  const ExgThree_vibrationTwoVer = dataForExg["Эксгаустер Ф-171"]['Подшипник[2]'].vibration_vertical.standart;

  const ExgThree_vibrationSevenAx = dataForExg["Эксгаустер Ф-171"]['Подшипник[7]'].vibration_axial.standart;
  const ExgThree_vibrationSevenHor = dataForExg["Эксгаустер Ф-171"]['Подшипник[7]'].vibration_horizontal.standart;
  const ExgThree_vibrationSevenVer = dataForExg["Эксгаустер Ф-171"]['Подшипник[7]'].vibration_vertical.standart;

  const ExgThree_vibrationEightAx = dataForExg["Эксгаустер Ф-171"]['Подшипник[8]'].vibration_axial.standart;
  const ExgThree_vibrationEightHor = dataForExg["Эксгаустер Ф-171"]['Подшипник[8]'].vibration_horizontal.standart;
  const ExgThree_vibrationEightVer = dataForExg["Эксгаустер Ф-171"]['Подшипник[8]'].vibration_vertical.standart;


  const ExgFour_vibrationOneAx = dataForExg["Эксгаустер Ф-172"]['Подшипник[1]'].vibration_axial.standart;
  const ExgFour_vibrationOneHor = dataForExg["Эксгаустер Ф-172"]['Подшипник[1]'].vibration_horizontal.standart;
  const ExgFour_vibrationOneVer = dataForExg["Эксгаустер Ф-172"]['Подшипник[1]'].vibration_vertical.standart;

  const ExgFour_vibrationTwoAx = dataForExg["Эксгаустер Ф-172"]['Подшипник[2]'].vibration_axial.standart;
  const ExgFour_vibrationTwoHor = dataForExg["Эксгаустер Ф-172"]['Подшипник[2]'].vibration_horizontal.standart;
  const ExgFour_vibrationTwoVer = dataForExg["Эксгаустер Ф-172"]['Подшипник[2]'].vibration_vertical.standart;

  const ExgFour_vibrationSevenAx = dataForExg["Эксгаустер Ф-172"]['Подшипник[7]'].vibration_axial.standart;
  const ExgFour_vibrationSevenHor = dataForExg["Эксгаустер Ф-172"]['Подшипник[7]'].vibration_horizontal.standart;
  const ExgFour_vibrationSevenVer = dataForExg["Эксгаустер Ф-172"]['Подшипник[7]'].vibration_vertical.standart;

  const ExgFour_vibrationEightAx = dataForExg["Эксгаустер Ф-172"]['Подшипник[8]'].vibration_axial.standart;
  const ExgFour_vibrationEightHor = dataForExg["Эксгаустер Ф-172"]['Подшипник[8]'].vibration_horizontal.standart;
  const ExgFour_vibrationEightVer = dataForExg["Эксгаустер Ф-172"]['Подшипник[8]'].vibration_vertical.standart;


  const ExgFive_vibrationOneAx = dataForExg["Эксгаустер Х-171"]['Подшипник[1]'].vibration_axial.standart;
  const ExgFive_vibrationOneHor = dataForExg["Эксгаустер Х-171"]['Подшипник[1]'].vibration_horizontal.standart;
  const ExgFive_vibrationOneVer = dataForExg["Эксгаустер Х-171"]['Подшипник[1]'].vibration_vertical.standart;

  const ExgFive_vibrationTwoAx = dataForExg["Эксгаустер Х-171"]['Подшипник[2]'].vibration_axial.standart;
  const ExgFive_vibrationTwoHor = dataForExg["Эксгаустер Х-171"]['Подшипник[2]'].vibration_horizontal.standart;
  const ExgFive_vibrationTwoVer = dataForExg["Эксгаустер Х-171"]['Подшипник[2]'].vibration_vertical.standart;

  const ExgFive_vibrationSevenAx = dataForExg["Эксгаустер Х-171"]['Подшипник[7]'].vibration_axial.standart;
  const ExgFive_vibrationSevenHor = dataForExg["Эксгаустер Х-171"]['Подшипник[7]'].vibration_horizontal.standart;
  const ExgFive_vibrationSevenVer = dataForExg["Эксгаустер Х-171"]['Подшипник[7]'].vibration_vertical.standart;

  const ExgFive_vibrationEightAx = dataForExg["Эксгаустер Х-171"]['Подшипник[8]'].vibration_axial.standart;
  const ExgFive_vibrationEightHor = dataForExg["Эксгаустер Х-171"]['Подшипник[8]'].vibration_horizontal.standart;
  const ExgFive_vibrationEightVer = dataForExg["Эксгаустер Х-171"]['Подшипник[8]'].vibration_vertical.standart;


  // const ExgSix_vibrationOneAx = dataForExg["Эксгаустер X-172"]['Подшипник[1]'].vibration_axial.standart;
  // const ExgSix_vibrationOneHor = dataForExg["Эксгаустер X-172"]['Подшипник[1]'].vibration_horizontal.standart;
  // const ExgSix_vibrationOneVer = dataForExg["Эксгаустер X-172"]['Подшипник[1]'].vibration_vertical.standart;

  // const ExgSix_vibrationTwoAx = dataForExg["Эксгаустер X-172"]['Подшипник[2]'].vibration_axial.standart;
  // const ExgSix_vibrationTwoHor = dataForExg["Эксгаустер X-172"]['Подшипник[2]'].vibration_horizontal.standart;
  // const ExgSix_vibrationTwoVer = dataForExg["Эксгаустер X-172"]['Подшипник[2]'].vibration_vertical.standart;

  // const ExgSix_vibrationSevenAx = dataForExg["Эксгаустер X-172"]['Подшипник[7]'].vibration_axial.standart;
  // const ExgSix_vibrationSevenHor = dataForExg["Эксгаустер X-172"]['Подшипник[7]'].vibration_horizontal.standart;
  // const ExgSix_vibrationSevenVer = dataForExg["Эксгаустер X-172"]['Подшипник[7]'].vibration_vertical.standart;

  // const ExgSix_vibrationEightAx = dataForExg["Эксгаустер X-172"]['Подшипник[8]'].vibration_axial.standart;
  // const ExgSix_vibrationEightHor = dataForExg["Эксгаустер X-172"]['Подшипник[8]'].vibration_horizontal.standart;
  // const ExgSix_vibrationEightVer = dataForExg["Эксгаустер X-172"]['Подшипник[8]'].vibration_vertical.standart;

  // const liquidAndTemperaturesIconStatus = () => {


  //     if(title==='Уровень масла'){
  //       console.log(JSON.stringify(data)[oil]);

  //       if(JSON.stringify(data)[oil] < 20 && JSON.stringify(data)[oil] > 10) {
  //         return `bearing__liquid_warning`;
  //       } else if (JSON.stringify(data)[oil] < 10)
  //         return `bearing__liquid_alarm`;
  //     } else {

  //       const temperature = keys[exhauster][`Подшипник[${title[1]}]`].temperature.standart;
  //       console.log((data)[temperature]);

  //       if(JSON.stringify(data)[temperature] >= 65 && JSON.stringify(data)[temperature] < 75)
  //         return `bearing__temperature_warning`;
  //       else if (JSON.stringify(data)[temperature] >= 75)
  //         return `bearing__temperature_alarm`;
  //     }

  // };

  console.log([oilFirstModify, oilSecondModify, oilThirdModify, oilFourthModify])

  const handleGoToMnenoshemePageClick = (exgausterName) => {
    setPaginationName(`Прогнозная аналитика эксгаустеров / Состояние эксгаустера ${exgausterName}`)
  }

  useEffect(() => {
    socket.current.onopen = () => {
      console.log('Opened Connection!');
      socket.current.send("READY");
      //Api.getInfo();
    };



    socket.current.onmessage = (event) => {
      let temp = JSON.parse(event.data);
      setData(temp);
      setOilFirstModify(oilImg(oilOne));
      setOilSecondModify(oilImg(oilTwo));
      setOilThirdModify(oilImg(oilThree));
      setOilFourthModify(oilImg(oilFour));

      setExgOneTemr([...exgOneTempr, temperatureImg(ExgOnetemperatureOne)])
      setExgOneTemr([...exgOneTempr, temperatureImg(ExgOnetemperatureTwo)])
      setExgOneTemr([...exgOneTempr, temperatureImg(ExgOnetemperatureThree)])
      setExgOneTemr([...exgOneTempr, temperatureImg(ExgOnetemperatureFour)])
      setExgOneTemr([...exgOneTempr, temperatureImg(ExgOnetemperatureFive)])
      setExgOneTemr([...exgOneTempr, temperatureImg(ExgOnetemperatureSix)])
      setExgOneTemr([...exgOneTempr, temperatureImg(ExgOnetemperatureSeven)])
      setExgOneTemr([...exgOneTempr, temperatureImg(ExgOnetemperatureEight)])
      setExgOneTemr([...exgOneTempr, temperatureImg(ExgOnetemperatureNine)]);





      console.log(exgOneTempr);
      console.log(JSON.stringify(data));
    }
    return () => socket.current.onclose = () => {
      console.log('Closed Connection!');
    };
  }, [data]);

  useEffect(() => {
    setOilFirstModify(oilImg(oilOne));
  })
  // }, [input, data]);

  return (
    <div className='page'>
      <Header pageName={paginationName} />
      <Switch>
        <Route key={'1'} exact path='/'>
          <MainPage
            data={data}
            handleMnemoshemeClick={handleGoToMnenoshemePageClick}
            boolCheck={boolCheck}
            oilFirst = {oilFirstModify}
            oilSecond = {oilSecondModify}
            oilThird = {oilThirdModify}
            oilFourth = {oilFourthModify}
            exgOneT_array = {exgOneTempr}
          />
          {/* {data["SM_Exgauster\\[8:1]"]} */}
        </Route>
        <Route key={'2'} path='/metrics'>
          <ExgausterScreen
            data={data}

          />
        </Route>
      </Switch>
    </div>
);}

export default App;
