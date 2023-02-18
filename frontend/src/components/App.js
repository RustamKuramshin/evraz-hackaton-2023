/// Нужно будет парсить значение, получаемое по ключу в float
// Spring bot стандартный и jdk 16
import React, { useEffect, useRef, useState } from 'react';
import { Route, Switch } from 'react-router-dom';
import Api from '../utils/Api';
import Header from './Header';
import MainPage from './MainPage';

function App() {
  const [data, setData] = useState({});
  const [paginationName, setPaginationName] = useState('Прогнозная аналитика эксгаустеров');
  //const socket = useRef(new WebSocket('ws://localhost:8080/'));
  const socket = useRef(new WebSocket('ws://51.250.23.216:38080/metrics'));

  //console.log([input, data]);

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
      console.log(data["SM_Exgauster\\[8:1]"]);

      // setInput(temp["SM_Exgauster\\[8:1]"]);
      // console.log(input);
    }

    return () => socket.current.onclose = () => {
      console.log('Closed Connection!');
    };
  }, [data]);
  // }, [input, data]);

  return (
    <div className='page'>
      <Header pageName={paginationName} />
      <Switch>
        <Route exact path='/'>
          <MainPage
            data={data}
            handleMnemoshemeClick={handleGoToMnenoshemePageClick}
          />
          {/* {data["SM_Exgauster\\[8:1]"]} */}
        </Route>
      </Switch>
    </div>
);}

export default App;
