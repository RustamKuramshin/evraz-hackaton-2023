import React from "react";

export default function Bearing({title}) {
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


  return(
    <div className="bearing">
      <p className="bearing__title">{title}</p>
      <div className="bearing__images">
        <div className={iconClassNameBearing}></div>
        <div className={iconClassNameVibration}></div>
      </div>
    </div>
  )
}