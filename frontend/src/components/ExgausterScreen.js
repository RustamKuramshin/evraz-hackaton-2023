//import './App.css';
import React, { useEffect, useRef, useState } from 'react';
import { withRouter } from 'react-router-dom';
//import './form2.css';
import GridLayout from "react-grid-layout";
const { dataForExg } = require('../utils/ExgaustersData');

class MyFirstGrid extends React.Component {
  constructor(props) {
    super(props);
    this._data = props.data;
  }
  render() {
    // layout is an array of objects, see the demo for more complete usage
    const layout = [
      { i: "pq", x: 0, y: 1, w: 1, h: 1, static: true},
      { i: "pa", x: 0, y: 2, w: 1, h: 1, static: true},
      { i: "pz", x: 0, y: 3, w: 1, h: 1, static: true},
      { i: "pw", x: 0, y: 4, w: 1, h: 1, static: true},
      { i: "ps", x: 0, y: 5, w: 1, h: 1, static: true},
      { i: "px", x: 0, y: 6, w: 1, h: 1, static: true},
      { i: "pe", x: 0, y: 7, w: 1, h: 1, static: true},
      { i: "pd", x: 0, y: 8, w: 1, h: 1, static: true},
      { i: "pc", x: 0, y: 9, w: 1, h: 1, static: true},



      { i: "a", x: 0, y: 0, w: 1, h: 1, static: true},
      { i: "b", x: 1, y: 0, w: 1, h: 1, static: true},
      { i: "c", x: 2, y: 0, w: 1, h: 1, static: true},
      { i: "d", x: 3, y: 0, w: 1, h: 1, static: true},
      { i: "q", x: 4, y: 0, w: 1, h: 1, static: true},

      { i: "z", x: 1, y: 1, w: 1, h: 1, static: true},
      { i: "w", x: 2, y: 1, w: 1, h: 1, static: true},
      { i: "s", x: 3, y: 1, w: 1, h: 1, static: true},
      { i: "x", x: 4, y: 1, w: 1, h: 1, static: true},
      { i: "e", x: 5, y: 1, w: 1, h: 1, static: true},
      { i: "r", x: 6, y: 1, w: 1, h: 1, static: true},

      { i: "v", x: 1, y: 2, w: 1, h: 1, static: true},
      { i: "t", x: 2, y: 2, w: 1, h: 1, static: true},
      { i: "g", x: 3, y: 2, w: 1, h: 1, static: true},
      { i: "y", x: 4, y: 2, w: 1, h: 1, static: true},
      { i: "h", x: 5, y: 2, w: 1, h: 1, static: true},
      { i: "n", x: 6, y: 2, w: 1, h: 1, static: true},

      { i: "j", x: 1, y: 3, w: 1, h: 1, static: true},
      { i: "m", x: 2, y: 3, w: 1, h: 1, static: true},
      { i: "o", x: 3, y: 3, w: 1, h: 1, static: true},
      { i: "l", x: 4, y: 3, w: 1, h: 1, static: true},
      { i: "p", x: 5, y: 3, w: 1, h: 1, static: true},
      { i: "aq", x: 6, y: 3, w: 1, h: 1, static: true},

      { i: "az", x: 1, y: 4, w: 1, h: 1, static: true},
      { i: "aw", x: 2, y: 4, w: 1, h: 1, static: true},
      { i: "as", x: 3, y: 4, w: 1, h: 1, static: true},
      { i: "ax", x: 4, y: 4, w: 1, h: 1, static: true},
      { i: "ae", x: 5, y: 4, w: 1, h: 1, static: true},
      { i: "ad", x: 6, y: 4, w: 1, h: 1, static: true},

      { i: "ar", x: 1, y: 5, w: 1, h: 1, static: true},
      { i: "af", x: 2, y: 5, w: 1, h: 1, static: true},
      { i: "av", x: 3, y: 5, w: 1, h: 1, static: true},
      { i: "at", x: 4, y: 5, w: 1, h: 1, static: true},
      { i: "ag", x: 5, y: 5, w: 1, h: 1, static: true},
      { i: "ab", x: 6, y: 5, w: 1, h: 1, static: true},

      { i: "ah", x: 1, y: 6, w: 1, h: 1, static: true},
      { i: "an", x: 2, y: 6, w: 1, h: 1, static: true},
      { i: "au", x: 3, y: 6, w: 1, h: 1, static: true},
      { i: "aj", x: 4, y: 6, w: 1, h: 1, static: true},
      { i: "am", x: 5, y: 6, w: 1, h: 1, static: true},
      { i: "ai", x: 6, y: 6, w: 1, h: 1, static: true},

      { i: "ak", x: 1, y: 7, w: 1, h: 1, static: true},
      { i: "ao", x: 2, y: 7, w: 1, h: 1, static: true},
      { i: "ap", x: 3, y: 7, w: 1, h: 1, static: true},
      { i: "qq", x: 4, y: 7, w: 1, h: 1, static: true},
      { i: "ww", x: 5, y: 7, w: 1, h: 1, static: true},
      { i: "qa", x: 6, y: 7, w: 1, h: 1, static: true},

      { i: "qz", x: 1, y: 8, w: 1, h: 1, static: true},
      { i: "qw", x: 2, y: 8, w: 1, h: 1, static: true},
      { i: "qs", x: 3, y: 8, w: 1, h: 1, static: true},
      { i: "qx", x: 4, y: 8, w: 1, h: 1, static: true},
      { i: "qe", x: 5, y: 8, w: 1, h: 1, static: true},
      { i: "qd", x: 6, y: 8, w: 1, h: 1, static: true},

      { i: "i", x: 1, y: 9, w: 1, h: 1, static: true},
      { i: "k", x: 2, y: 9, w: 1, h: 1, static: true},
      { i: "qc", x: 3, y: 9, w: 1, h: 1, static: true},
      { i: "qr", x: 4, y: 9, w: 1, h: 1, static: true},
      { i: "qf", x: 5, y: 9, w: 1, h: 1, static: true},
      { i: "qv", x: 6, y: 9, w: 1, h: 1, static: true},

      { i: "i", x: 1, y: 9, w: 1, h: 1, static: true},
      { i: "k", x: 2, y: 9, w: 1, h: 1, static: true},
      { i: "qc", x: 3, y: 9, w: 1, h: 1, static: true},
      { i: "qr", x: 4, y: 9, w: 1, h: 1, static: true},
      { i: "qf", x: 5, y: 9, w: 1, h: 1, static: true},
      { i: "qv", x: 6, y: 9, w: 1, h: 1, static: true},

      { i: "sg", x: 0, y: 10, w: 1, h: 1, static: true},
      { i: "ss", x: 1, y: 10, w: 1, h: 1, static: true},
      { i: "sx", x: 2, y: 10, w: 1, h: 1, static: true},
      { i: "sf", x: 3, y: 10, w: 1, h: 1, static: true},
      { i: "qt", x: 0, y: 11, w: 1, h: 1, static: true},
      { i: "qg", x: 1, y: 11, w: 1, h: 1, static: true},
      { i: "qb", x: 2, y: 11, w: 1, h: 1, static: true},
      { i: "qy", x: 3, y: 11, w: 1, h: 1, static: true},
    ];
    return (
      <GridLayout
        className="layout"
        layout={layout}
        cols={15}
        rowHeight={30}
        width={1500}


      >

        <div key="a">Подшибник </div>
        <div key="b">T,C</div>
        <div key="c">В.,мм.с</div>
        <div key="d">Г.,мм.с</div>
        <div key="q">О.,мм.с</div>
        <div key="q">О.,мм.с</div>
        <div key="pq">Подшибник № 1 </div>
        <div key="pa">Подшибник № 2</div>
        <div key="pz">Подшибник № 3</div>
        <div key="pw">Подшибник № 4</div>
        <div key="ps">Подшибник № 5</div>
        <div key="px">Подшибник № 6 </div>
        <div key="pe">Подшибник № 7</div>
        <div key="pd">Подшибник № 8</div>
        <div key="pc">Подшибник № 9</div>

        <div key="z">{this._data[dataForExg["Эксгаустер У-171"]["Подшипник[1]"].temperature.standart]}</div>
        <div key="w">{this._data[dataForExg["Эксгаустер У-171"]["Подшипник[1]"].vibration_vertical.standart]}</div>
        <div key="s">{this._data[dataForExg["Эксгаустер У-171"]["Подшипник[1]"].vibration_horizontal]}</div>
        <div key="x">{this._data[dataForExg["Эксгаустер У-171"]["Подшипник[1]"].vibration_axial.standart]}</div>
        <div key="e">{this._data[dataForExg["Эксгаустер У-171"]["Охладитель"].liquid]}</div>
        <div key="r">{this._data[dataForExg["Эксгаустер У-171"]["Охладитель"].water]}</div>

        <div key="v">{this._data[dataForExg["Эксгаустер У-171"]["Подшипник[2]"].temperature.standart]}</div>
        <div key="t">{this._data[dataForExg["Эксгаустер У-171"]["Подшипник[2]"].vibration_vertical.standart]}</div>
        <div key="g">{this._data[dataForExg["Эксгаустер У-171"]["Подшипник[2]"].vibration_horizontal]}</div>
        <div key="y">{this._data[dataForExg["Эксгаустер У-171"]["Подшипник[2]"].vibration_axial.standart]}</div>
        <div key="h">{this._data[dataForExg["Эксгаустер У-171"]["Охладитель"].liquid]}</div>
        <div key="n">{this._data[dataForExg["Эксгаустер У-171"]["Охладитель"].water]}</div>

        <div key="j">{this._data[dataForExg["Эксгаустер У-171"]["Подшипник[3]"].temperature.standart]}</div>
        <div key="m">{this._data[dataForExg["Эксгаустер У-171"]["Подшипник[3]"].temperature.alarm]}</div>
        <div key="i">{this._data[dataForExg["Эксгаустер У-171"]["Подшипник[3]"].temperature.warning]}</div>
        <div key="k">0.00</div>
        <div key="o">0.00</div>
        <div key="l">0.00</div>

        <div key="p">{this._data[dataForExg["Эксгаустер У-171"]["Подшипник[4]"].temperature.standart]}</div>
        <div key="aq">{this._data[dataForExg["Эксгаустер У-171"]["Подшипник[4]"].temperature.alarm]}</div>
        <div key="az">{this._data[dataForExg["Эксгаустер У-171"]["Подшипник[4]"].temperature.warning]}</div>
        <div key="aw">0.00</div>
        <div key="as">0.00</div>
        <div key="ax">0.00</div>

        <div key="ae">{this._data[dataForExg["Эксгаустер У-171"]["Подшипник[5]"].temperature.standart]}</div>
        <div key="ad">{this._data[dataForExg["Эксгаустер У-171"]["Подшипник[5]"].temperature.alarm]}</div>
        <div key="ar">{this._data[dataForExg["Эксгаустер У-171"]["Подшипник[5]"].temperature.warning]}</div>
        <div key="af">0.00</div>
        <div key="av">0.00</div>
        <div key="at">0.00</div>

        <div key="ag">{this._data[dataForExg["Эксгаустер У-171"]["Подшипник[6]"].temperature.standart]}</div>
        <div key="ab">{this._data[dataForExg["Эксгаустер У-171"]["Подшипник[6]"].temperature.alarm]}</div>
        <div key="ah">{this._data[dataForExg["Эксгаустер У-171"]["Подшипник[6]"].temperature.warning]}</div>
        <div key="an">0.00</div>
        <div key="au">0.00</div>
        <div key="aj">0.00</div>

        <div key="am">{this._data[dataForExg["Эксгаустер У-171"]["Подшипник[7]"].temperature.standart]}</div>
        <div key="ai">{this._data[dataForExg["Эксгаустер У-171"]["Подшипник[7]"].vibration_vertical.standart]}</div>
        <div key="ak">{this._data[dataForExg["Эксгаустер У-171"]["Подшипник[7]"].vibration_horizontal.standart]}</div>
        <div key="ao">{this._data[dataForExg["Эксгаустер У-171"]["Подшипник[7]"].vibration_axial.standart]}</div>
        <div key="ap">{this._data[dataForExg["Эксгаустер У-171"]["Охладитель"].liquid]}</div>
        <div key="qq">{this._data[dataForExg["Эксгаустер У-171"]["Охладитель"].water]}</div>

        <div key="qa">{this._data[dataForExg["Эксгаустер У-171"]["Подшипник[8]"].temperature.standart]}</div>
        <div key="qz">{this._data[dataForExg["Эксгаустер У-171"]["Подшипник[8]"].vibration_vertical.standart]}</div>
        <div key="qw">{this._data[dataForExg["Эксгаустер У-171"]["Подшипник[8]"].vibration_horizontal.standart]}</div>
        <div key="qs">{this._data[dataForExg["Эксгаустер У-171"]["Подшипник[8]"].vibration_axial.standart]}</div>
        <div key="qx">{this._data[dataForExg["Эксгаустер У-171"]["Охладитель"].liquid]}</div>
        <div key="qe">{this._data[dataForExg["Эксгаустер У-171"]["Охладитель"].water]}</div>

        <div key="qd">{this._data[dataForExg["Эксгаустер У-171"]["Подшипник[9]"].temperature.standart]}</div>
        <div key="qc">{this._data[dataForExg["Эксгаустер У-171"]["Подшипник[9]"].temperature.alarm]}</div>
        <div key="qr">{this._data[dataForExg["Эксгаустер У-171"]["Подшипник[9]"].temperature.warning]}</div>
        <div key="qf">0.00</div>
        <div key="qv">0.00</div>

        <div key="ww">0.00</div>
        <div key="qv">0.00</div>

        <div key="sg">Охладитель</div>
        <div key="ss">Входная температура</div>
        <div key="sx">Входная температура</div>
        <div key="sf">Выходная температура </div>
        <div key="gt">Охладитель</div>
        <div key="qg">0.00</div>
        <div key="qb">0.00</div>
        <div key="qy">0.00</div>


      </GridLayout>
    );
  }
}
function Form21() {
  const styles = {
    container: {
        backgroundPosition: 'left',
        backgroundSize: 'auto auto',
        backgroundRepeat: 'no-repeat',
        width: '100vw',
        height: '100vh',
        position: 'relative'
    },


  };
  return (

    <div style={styles.container}>
  <div class="wrapper">
   <div>One</div>
   <div>Two</div>
   <div>Three</div>
   <div>Four</div>
   <div>Five</div>
    </div>

     </div>

     )

  }


 export default withRouter(MyFirstGrid);