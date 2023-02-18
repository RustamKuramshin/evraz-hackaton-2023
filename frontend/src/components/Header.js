import logo from "../images/header_Logo.png";

export default function Header ({pageName}) {
  return (
    <header className="header">
      <div className="header__left-group">
        <div className="header__logo-sector">
          <button className="header__menu-btn"></button>
          <div className="header__separator"></div>
          <img className="header__logo" src={logo} alt="Логотип компании ЕВРАЗ"/>
        </div>
        <h1 className="header__name-page">
          {pageName}
        </h1>
      </div>
      <div className="header__right-group">
        <button className="header__avatar"></button>
        <div className="header__drop-down-list"></div>
      </div>
    </header>
  )
}