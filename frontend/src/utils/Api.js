class Api {
  constructor({link, headers}) {
    this._link = link;
    this._headers = headers;
  }

  getInfo() {
    return fetch(`${this._link}/api/v1/info`, {
      method: 'GET',
      headers: this._headers,
    })
      .then(this.checkErrors);
  }

  checkErrors(res) {
    if (res.ok) {
      return res.json();
    }
    return Promise.reject(`Ошибка: ${res.status}`);
  }
}

export default new Api({
  link: 'http://51.250.23.216:38080',
  headers: {
    'Content-Type': 'application/json',
  }
});
