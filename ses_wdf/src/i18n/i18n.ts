import i18n from 'i18next';
  import { initReactI18next } from 'react-i18next';

  i18n
    .use(initReactI18next)
    .init({
      resources: {
        en: {
          translation: {
          },
        },
        vi: {
          translation: {
          },
        },
      },
      lng: 'en',
      fallbackLng: 'en',
      interpolation: {
        escapeValue: false,
      },
      backend: {
        loadPath: '/locales/{{lng}}.json',
      },
    });

  export default i18n;