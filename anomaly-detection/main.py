import pandas as pd
from sklearn.cluster import DBSCAN
from influxdb_client import InfluxDBClient
from adtk.detector import MinClusterDetector
from sklearn.cluster import Birch
from adtk.visualization import plot

client = InfluxDBClient(url="http://51.250.23.216:8086")
query = '''from(bucket: "metrics")
  |> range(start: 2020-06-18T18:00:00Z ,  stop: 2020-06-20T02:00:00Z)
  |> filter(fn: (r) => r["_measurement"] == "SM_Exgauster\[3:18]")
  |> filter(fn: (r) => r["_field"] == "SM_Exgauster\[3:19]")
  |> pivot(rowKey:["_time"], columnKey: ["SM_Exgauster\[3:21]"], valueColumn: "_value")
  |> drop(columns:["_start", "_stop", "host", "_field", "_measurement"])'''

query_api = client.query_api()
df = query_api.query_data_frame(query)
df.head()

df["_time"] = pd.to_datetime(df["_time"].astype(str))
df = df.drop(columns=["result", "table"])
df = df.set_index("_time")
df.head()

min_cluster_detector = MinClusterDetector(Birch(n_clusters=10))
anomalies = min_cluster_detector.fit_detect(df)
plot(df, anomaly=anomalies, ts_linewidth=1, ts_markersize=3, anomaly_color='red', anomaly_alpha=0.3, curve_group='all')

plot(df[500:525], anomaly=anomalies[500:525], ts_linewidth=2, ts_markersize=4, anomaly_color='red', anomaly_alpha=2.0, curve_group='all')