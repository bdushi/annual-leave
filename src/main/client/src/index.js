import React from 'react';
import ReactDOM from 'react-dom';
import './index.css';
import App from './App';
import reportWebVitals from './reportWebVitals';
import 'bootstrap/dist/css/bootstrap.css';
import axios from "axios"

ReactDOM.render(
  <React.StrictMode>
    <App />
  </React.StrictMode>,
  document.getElementById('root')
);
axios.interceptors.request.use(function (config) {
  config.headers.Authorization = localStorage.getItem("token");
  // config.headers.Authorization = "Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJSUzI1NiJ9.eyJzdWIiOiJhZG1pbiIsIm5iZiI6MTYwNTg5Njc5OCwiaWQiOjUsImV4cCI6MTYwNTk4MzE5OCwiaWF0IjoxNjA1ODk2Nzk4LCJhdXRob3JpdGllcyI6WyJBRE1JTiJdLCJ1c2VybmFtZSI6ImFkbWluIn0.WMBVWeyzXDZND4ue0gq_sR2tyIFZEpCsycRlQa-sTu3GJ6mjiVJ8mwnp3kchweBvSmgW4ry2KuwcL205SRK5mykbJVF6CZ872okdClekLV7KFH5KD7IOLOG8ZIhjuYbFKPNRTETX_i2cnHM9r4acpVJMTUNclA1NtsLhGLye59kuo0YaxDpf2tWio7rPnRMQDauDwrmWuIe_DI_-5hq1SVvosucKjHjn_vZke2fbdgf5UCgchQwjB9jVO3O0JAtoF1Mq5y6aKU1YXx1RYgmkjgAp7aPzTuN5dojtUH9yvPjlIznoHoi2L45pV8qqg2YLD_1llOjNavGbRy-Mvfm6mg"
  config.baseURL = "http://localhost:8080";
  return config;
});
// If you want to start measuring performance in your app, pass a function
// to log results (for example: reportWebVitals(console.log))
// or send to an analytics endpoint. Learn more: https://bit.ly/CRA-vitals
reportWebVitals();
