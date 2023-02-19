import React, { useEffect, useState } from "react";

export default function Bearing({
  title,
  data,
  keys,
  exhauster,
  boolCheck
}) {

  if(boolCheck){
    const oil = keys[exhauster]["Маслосистема"].oil_level;
    const temperature = keys[exhauster][`Подшипник[${title[1]}]`].temperature.standart;
    console.log((data)[temperature]);
    console.log((data)[oil]);
  }
  const isVibrationThere = title === '№1  п-к' || title === '№2  п-к' || title === '№7  п-к' || title === '№8  п-к';
  const isThisLiquid = title === 'Уровень масла';

  const iconClassNameBearing = (
    `${isThisLiquid ?
      'bearing__liquid-image' :
      `bearing__temperature-image`}`
  );

  const iconClassNameVibration = (
    `bearing__vibration-image
      ${isVibrationThere ?
      '' :
      `bearing__vibration-image_disabled`}`
  );

  const liquidAndTemperaturesIconStatus = () => {
    if(boolCheck){
      const oil = keys[exhauster]["Маслосистема"].oil_level;
      const temperature = keys[exhauster][`Подшипник[${title[1]}]`].temperature.standart;
      console.log((data)[temperature]);
      console.log((data)[oil]);

      if(title==='Уровень масла'){
        if(JSON.stringify(data)[oil] < 20 && JSON.stringify(data)[oil] > 10) {
          return `bearing__liquid_warning`;
        } else if (JSON.stringify(data)[oil] < 10)
          return `bearing__liquid_alarm`;
      } else {
        if(JSON.stringify(data)[temperature] >= 65 && JSON.stringify(data)[temperature] < 75)
          return `bearing__temperature_warning`;
        else if (JSON.stringify(data)[temperature] >= 75)
          return `bearing__temperature_alarm`;
      }
    }
     else {
      return '';
    }
  };

  return(
    <div className="bearing">
      <p className="bearing__title">{title}</p>
      <div className="bearing__images">
        <div className={`${iconClassNameBearing} ${liquidAndTemperaturesIconStatus}`}></div>
        <div className={iconClassNameVibration}></div>
      </div>
    </div>
  )
}