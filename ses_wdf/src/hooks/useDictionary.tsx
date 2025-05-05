import { useTranslation } from 'react-i18next';

const useDictionary = (dictionaryData: any, key: string) => {
  const { t } = useTranslation();
  const viText = dictionaryData?.vi?.[key] ?? t(key, { lng: 'vi' });
  const engText = dictionaryData?.eng?.[key] ?? t(key, { lng: 'eng' });

  return `${viText} / ${engText}`;
};

export default useDictionary;
