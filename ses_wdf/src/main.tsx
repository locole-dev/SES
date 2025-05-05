import React from 'react';
import ReactDOM from 'react-dom/client';
import { BrowserRouter } from 'react-router-dom';
import App from './App';
import './css/style.css';
import './css/satoshi.css';
import './css/be-vietnam-pro.css';
import 'jsvectormap/dist/css/jsvectormap.css';
import 'flatpickr/dist/flatpickr.min.css';
import './i18n/i18n.ts'

ReactDOM.createRoot(document.getElementById('root') as HTMLElement).render(
  <React.StrictMode>
    <BrowserRouter basename="/gui">
      <App />
    </BrowserRouter>
  </React.StrictMode>,
);
