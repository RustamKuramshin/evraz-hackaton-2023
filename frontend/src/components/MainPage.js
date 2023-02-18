import React from "react";
import headingLogo from '../images/doc_Icon.svg';
import legend from '../images/legend.svg';
import ellips_red from '../images/Ellipse_red.svg';
import ellips_green from '../images/Ellipse_green.svg';
import schema from '../images/Scheme.svg'

import Bearing from "./Bearing";

export default function MainPage({handleMnemoshemeClick, data}) {
  const selectEllipseStatus = (nameExgauster) => {
    if(data[nameExgauster] === 1)
      return ellips_green;
    else
      return ellips_red;
  }

  return(
    <main className="content">
      <section className="heading">
        <img className="heading__logo" src={headingLogo} alt="Изображение документа"/>
        <p className="heading__text">Главный экран</p>
      </section>

      <section className="aglomachines">
        <div className="aglomachines__legend"></div>
        <ul className="aglomachines__elements">
          <li className="aglomachines__list"> {/*1 агломашина с 2-мя эксг*/}
          <p className="aglomachines__title">Агломашина №1</p>
            <div className="exgausters">
              <div className="exgauster"> {/*1 из эксгаустеров в машине*/}
                <div className="exgauster__heading">
                  <img className="exgauster__status-image" src={selectEllipseStatus("SM_Exgauster\\[2.0]")} alt="Эллипс статуса работы эксгаустера"/>
                  <p className="exgauster__name">Эксгаустер У-171</p>
                  <button
                    className="exgauster__btn"
                    onClick={() => {
                      handleMnemoshemeClick('Эксгаустера У-171')
                    }}
                  ></button>
                </div>
                <div className="rotor">
                  <div className="rotor__heading">
                    <p className="rotor__name">Ротор № 35к</p>
                    <p className="rotor__date">12.02.2022</p>
                    <a className="rotor__changing-link" href="#">Изменить</a>
                  </div>
                  <p className="rotor__replacement-title">Последняя замена ротера</p>
                  <div className="rotor__replacement">
                    <p className="rotor__replacement-date">6 сут</p>
                    <div className="rotor__prognosis">
                      <p className="rotor__prognosis-title">Прогноз</p>
                      <p className="rotor__prognosis-days">12 сут</p>
                    </div>
                  </div>
                  <img className="rotor__image" src={schema} alt='Схема эксгаустера' />
                  <div className="bearing__dropdown">
                    <button className="bearing__btn"></button>
                    <p className="bearing__heading">Все подшипники</p>
                  </div>
                  <Bearing
                    title={'№1  п-к'}
                  />
                  <Bearing title={'№2  п-к'}/>
                  <Bearing title={'№3  п-к'}/>
                  <Bearing title={'№4  п-к'}/>
                  <Bearing title={'№5  п-к'}/>
                  <Bearing title={'№6  п-к'}/>
                  <Bearing title={'№7  п-к'}/>
                  <Bearing title={'№8  п-к'}/>
                  <Bearing title={'№9  п-к'}/>
                  <Bearing title={'Уровень масла'}/>
                </div>
              </div>

              <div className="exgauster"> {/*1 из эксгаустеров в машине*/}
                <div className="exgauster__heading">
                  <img className="exgauster__status-image" src={selectEllipseStatus("SM_Exgauster\\[2.1]")} alt="Эллипс статуса работы эксгаустера"/>
                  <p className="exgauster__name">Эксгаустер У-172</p>
                  <button
                    className="exgauster__btn"
                    onClick={() => {
                      handleMnemoshemeClick('Эксгаустера У-172')
                    }}
                  ></button>
                </div>
                <div className="rotor">
                  <div className="rotor__heading">
                    <p className="rotor__name">Ротор № 47к</p>
                    <p className="rotor__date">25.02.2022</p>
                    <a className="rotor__changing-link" href="#">Изменить</a>
                  </div>
                  <p className="rotor__replacement-title">Последняя замена ротера</p>
                  <div className="rotor__replacement">
                    <p className="rotor__replacement-date">20 сут</p>
                    <div className="rotor__prognosis">
                      <p className="rotor__prognosis-title">Прогноз</p>
                      <p className="rotor__prognosis-days">25 сут</p>
                    </div>
                  </div>
                  <img className="rotor__image" src={schema} alt='Схема эксгаустера' />
                  <div className="bearing__dropdown">
                    <button className="bearing__btn"></button>
                    <p className="bearing__heading">Все подшипники</p>
                  </div>
                  <Bearing title={'№1  п-к'}/>
                  <Bearing title={'№2  п-к'}/>
                  <Bearing title={'№3  п-к'}/>
                  <Bearing title={'№4  п-к'}/>
                  <Bearing title={'№5  п-к'}/>
                  <Bearing title={'№6  п-к'}/>
                  <Bearing title={'№7  п-к'}/>
                  <Bearing title={'№8  п-к'}/>
                  <Bearing title={'№9  п-к'}/>
                  <Bearing title={'Уровень масла'}/>
                </div>
              </div>
            </div>
          </li>

{/* ///////////////////////////////////////////////////////////////////////// */}

          <li className="aglomachines__list"> {/*1 агломашина с 2-мя эксг*/}
            <p className="aglomachines__title">Агломашина №2</p>
            <div className="exgausters">
              <div className="exgauster"> {/*1 из эксгаустеров в машине*/}
                <div className="exgauster__heading">
                  <img className="exgauster__status-image" src={selectEllipseStatus("SM_Exgauster\\[0.0]")} alt="Эллипс статуса работы эксгаустера"/>
                  <p className="exgauster__name">Эксгаустер Ф-171</p>
                  <button
                    className="exgauster__btn"
                    onClick={() => {
                      handleMnemoshemeClick('Эксгаустера Ф-171')
                    }}
                  ></button>
                </div>
                <div className="rotor">
                  <div className="rotor__heading">
                    <p className="rotor__name">Ротор № 37</p>
                    <p className="rotor__date">12.02.2022</p>
                    <a className="rotor__changing-link" href="#">Изменить</a>
                  </div>
                  <p className="rotor__replacement-title">Последняя замена ротера</p>
                  <div className="rotor__replacement">
                    <p className="rotor__replacement-date">30 сут</p>
                    <div className="rotor__prognosis">
                      <p className="rotor__prognosis-title">Прогноз</p>
                      <p className="rotor__prognosis-days">10 сут</p>
                    </div>
                  </div>
                  <img className="rotor__image" src={schema} alt='Схема эксгаустера' />
                  <div className="bearing__dropdown">
                    <button className="bearing__btn"></button>
                    <p className="bearing__heading">Все подшипники</p>
                  </div>
                  <Bearing title={'№1  п-к'}/>
                  <Bearing title={'№2  п-к'}/>
                  <Bearing title={'№3  п-к'}/>
                  <Bearing title={'№4  п-к'}/>
                  <Bearing title={'№5  п-к'}/>
                  <Bearing title={'№6  п-к'}/>
                  <Bearing title={'№7  п-к'}/>
                  <Bearing title={'№8  п-к'}/>
                  <Bearing title={'№9  п-к'}/>
                  <Bearing title={'Уровень масла'}/>
                </div>
              </div>

              <div className="exgauster"> {/*1 из эксгаустеров в машине*/}
                <div className="exgauster__heading">
                  <img className="exgauster__status-image" src={selectEllipseStatus("SM_Exgauster\\[0.1]")} alt="Эллипс статуса работы эксгаустера"/>
                  <p className="exgauster__name">Эксгаустер Ф-172</p>
                  <button
                    className="exgauster__btn"
                    onClick={() => {
                      handleMnemoshemeClick('Эксгаустера Ф-172')
                    }}
                  ></button>
                </div>
                <div className="rotor">
                  <div className="rotor__heading">
                    <p className="rotor__name">Ротор № 32</p>
                    <p className="rotor__date">25.02.2022</p>
                    <a className="rotor__changing-link" href="#">Изменить</a>
                  </div>
                  <p className="rotor__replacement-title">Последняя замена ротера</p>
                  <div className="rotor__replacement">
                    <p className="rotor__replacement-date">15 сут</p>
                    <div className="rotor__prognosis">
                      <p className="rotor__prognosis-title">Прогноз</p>
                      <p className="rotor__prognosis-days">16 сут</p>
                    </div>
                  </div>
                  <img className="rotor__image" src={schema} alt='Схема эксгаустера' />
                  <div className="bearing__dropdown">
                    <button className="bearing__btn"></button>
                    <p className="bearing__heading">Все подшипники</p>
                  </div>
                  <Bearing title={'№1  п-к'}/>
                  <Bearing title={'№2  п-к'}/>
                  <Bearing title={'№3  п-к'}/>
                  <Bearing title={'№4  п-к'}/>
                  <Bearing title={'№5  п-к'}/>
                  <Bearing title={'№6  п-к'}/>
                  <Bearing title={'№7  п-к'}/>
                  <Bearing title={'№8  п-к'}/>
                  <Bearing title={'№9  п-к'}/>
                  <Bearing title={'Уровень масла'}/>
                </div>
              </div>
            </div>
          </li>
{/* ///////////////////////////////////////////////////////////////////////// */}
          <li className="aglomachines__list"> {/*1 агломашина с 2-мя эксг*/}
            <p className="aglomachines__title">Агломашина №3</p>
            <div className="exgausters">
              <div className="exgauster"> {/*1 из эксгаустеров в машине*/}
                <div className="exgauster__heading">
                  <img className="exgauster__status-image" src={selectEllipseStatus("SM_Exgauster\\[3.0]")} alt="Эллипс статуса работы эксгаустера"/>
                  <p className="exgauster__name">Эксгаустер Х-171</p>
                  <button
                    className="exgauster__btn"
                    onClick={() => {
                      handleMnemoshemeClick('Эксгаустера Х-171')
                    }}
                  ></button>
                </div>
                <div className="rotor">
                  <div className="rotor__heading">
                    <p className="rotor__name">Ротор № 24</p>
                    <p className="rotor__date">12.02.2022</p>
                    <a className="rotor__changing-link" href="#">Изменить</a>
                  </div>
                  <p className="rotor__replacement-title">Последняя замена ротера</p>
                  <div className="rotor__replacement">
                    <p className="rotor__replacement-date">3 сут</p>
                    <div className="rotor__prognosis">
                      <p className="rotor__prognosis-title">Прогноз</p>
                      <p className="rotor__prognosis-days">10 сут</p>
                    </div>
                  </div>
                  <img className="rotor__image" src={schema} alt='Схема эксгаустера' />
                  <div className="bearing__dropdown">
                    <button className="bearing__btn"></button>
                    <p className="bearing__heading">Все подшипники</p>
                  </div>
                  <Bearing title={'№1  п-к'}/>
                  <Bearing title={'№2  п-к'}/>
                  <Bearing title={'№3  п-к'}/>
                  <Bearing title={'№4  п-к'}/>
                  <Bearing title={'№5  п-к'}/>
                  <Bearing title={'№6  п-к'}/>
                  <Bearing title={'№7  п-к'}/>
                  <Bearing title={'№8  п-к'}/>
                  <Bearing title={'№9  п-к'}/>
                  <Bearing title={'Уровень масла'}/>
                </div>
              </div>

              <div className="exgauster"> {/*1 из эксгаустеров в машине*/}
                <div className="exgauster__heading">
                  <img className="exgauster__status-image" src={selectEllipseStatus("SM_Exgauster\\[3.1]")} alt="Эллипс статуса работы эксгаустера"/>
                  <p className="exgauster__name">Эксгаустер Х-172</p>
                  <button
                    className="exgauster__btn"
                    onClick={() => {
                      handleMnemoshemeClick('Эксгаустера Х-172')
                    }}
                  ></button>
                </div>
                <div className="rotor">
                  <div className="rotor__heading">
                    <p className="rotor__name">Ротор № 22к</p>
                    <p className="rotor__date">25.02.2022</p>
                    <a className="rotor__changing-link" href="#">Изменить</a>
                  </div>
                  <p className="rotor__replacement-title">Последняя замена ротера</p>
                  <div className="rotor__replacement">
                    <p className="rotor__replacement-date">2 сут</p>
                    <div className="rotor__prognosis">
                      <p className="rotor__prognosis-title">Прогноз</p>
                      <p className="rotor__prognosis-days">15 сут</p>
                    </div>
                  </div>
                  <img className="rotor__image" src={schema} alt='Схема эксгаустера' />
                  <div className="bearing__dropdown">
                    <button className="bearing__btn"></button>
                    <p className="bearing__heading">Все подшипники</p>
                  </div>
                  <Bearing title={'№1  п-к'}/>
                  <Bearing title={'№2  п-к'}/>
                  <Bearing title={'№3  п-к'}/>
                  <Bearing title={'№4  п-к'}/>
                  <Bearing title={'№5  п-к'}/>
                  <Bearing title={'№6  п-к'}/>
                  <Bearing title={'№7  п-к'}/>
                  <Bearing title={'№8  п-к'}/>
                  <Bearing title={'№9  п-к'}/>
                  <Bearing title={'Уровень масла'}/>
                </div>
              </div>
            </div>
          </li>
{/* ///////////////////////////////////////////////////////////////////////// */}
        </ul>
      </section>
    </main>
  );
}