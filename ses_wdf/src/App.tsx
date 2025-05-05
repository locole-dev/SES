import { useEffect, useState } from 'react';
import { Route, Routes, useLocation } from 'react-router-dom';

import Loader from './common/Loader';
import PageTitle from './components/PageTitle';
import SignIn from './pages/Authentication/SignIn';
import ECommerce from './pages/Dashboard/ECommerce';
import Buttons from './pages/UiElements/Buttons';
import DefaultLayout from './layout/DefaultLayout';
import Carriers from './pages/Carriers/Carriers';
// import Logs from './pages/Logs/Logs';
import CarrierInsert from './pages/Carriers/CarrierInsert';
import Colors from './pages/UiElements/Colors';
import Fonts from './pages/UiElements/Fonts';
import Smartwatch from './pages/Smartwatch/Smartwatch';
import SmartwatchInsert from './pages/Smartwatch/SmartwatchInsert';
import FileManager from './pages/FileManager';
import Dictionary from './pages/Dictionary/Dictionary';
import CarrierMaps from './pages/Carriers/CarrierMaps';
import CarrierConfig from './pages/Carriers/CarrierConfig';
import DictionaryInsert from './pages/Dictionary/DictionaryInsert';
import DictionaryUpdate from './pages/Dictionary/DictionaryUpdate.tsx';
import PrivateRoute from './util/PrivateRoute';

function App() {
  const [loading, setLoading] = useState<boolean>(true);
  const { pathname } = useLocation();

  useEffect(() => {
    window.scrollTo(0, 0);
  }, [pathname]);

  useEffect(() => {
    setTimeout(() => setLoading(false), 1000);
  }, []);

  return loading ? (
    <Loader />
  ) : (

    <Routes>
      <Route
        path="/auth/signin"
        element={
          <>
            <PageTitle title="Signin | SES WDF" />
            <SignIn />
          </>
        }
      />
      <Route path='/*' element={
        <PrivateRoute>
          <DefaultLayout>
            <Routes>
              <Route
                index
                element={
                  <>
                    <PageTitle title="Analytics | SES WDF" />
                    <ECommerce />
                  </>
                }
              />
              <Route
                path="/pages/carriers"
                element={
                  <>
                    <PageTitle title="Carriers | SES WDF" />
                    <Carriers />
                  </>
                }
              />
              <Route
                path="/pages/carriers/form"
                element={
                  <>
                    <PageTitle title="Carrier Form | SES WDF" />
                    <CarrierInsert />
                  </>
                }
              />
              <Route
                path="/pages/carriers/form/:id"
                element={
                  <>
                    <PageTitle title="Carrier Form | SES WDF" />
                    <CarrierInsert />
                  </>
                }
              />
              <Route
                path="/pages/carriers/mappings/:id"
                element={
                  <>
                    <PageTitle title="Carrier Mappings | SES WDF" />
                    <CarrierMaps />
                  </>
                }
              />
              <Route
                path="/pages/carriers/:carrierId/config/:id"
                element={
                  <>
                    <PageTitle title="Carrier Config | SES WDF" />
                    <CarrierConfig />
                  </>
                }
              />
              <Route
                path="/pages/carriers/:carrierId/config"
                element={
                  <>
                    <PageTitle title="Carrier Config | SES WDF" />
                    <CarrierConfig />
                  </>
                }
              />
              <Route
                path="/pages/smartwatch"
                element={
                  <>
                    <PageTitle title="Smartwatch | SES WDF" />
                    <Smartwatch />
                  </>
                }
              />
              <Route
                path="/pages/smartwatch/form"
                element={
                  <>
                    <PageTitle title="Carrier Form | SES WDF" />
                    <SmartwatchInsert />
                  </>
                }
              />
              <Route
                path="/pages/smartwatch/form/:id"
                element={
                  <>
                    <PageTitle title="Carrier Form | SES WDF" />
                    <SmartwatchInsert />
                  </>
                }
              />
              <Route
                path="/settings/file-manager"
                element={
                  <>
                    <PageTitle title="File Manager | SES WDF" />
                    <FileManager />
                  </>
                }
              />
              <Route
                path="/settings/dictionary"
                element={
                  <>
                    <PageTitle title="Dictionary | SES WDF" />
                    <Dictionary />
                  </>
                }
              />
              <Route
                path="/settings/dictionary/form"
                element={
                  <>
                    <PageTitle title="Dictionary Form | SES WDF" />
                    <DictionaryInsert />
                  </>
                }
              />
              <Route
                path="/settings/dictionary/form/:id"
                element={
                  <>
                    <PageTitle title="Dictionary Form | SES WDF" />
                      <DictionaryUpdate />
                  </>
                }
              />
              <Route
                path="/ui/fonts"
                element={
                  <>
                    <PageTitle title="Fonts | SES WDF" />
                    <Fonts />
                  </>
                }
              />
              <Route
                path="/ui/buttons-colors"
                element={
                  <>
                    <PageTitle title="Buttons & Colors | SES WDF" />
                    <Buttons />
                  </>
                }
              />
              <Route
                path="/ui/colors"
                element={
                  <>
                    <PageTitle title="Colors | SES WDF" />
                    <Colors />
                  </>
                }
              />

              <Route
                path="/logs"
                element={
                  <>
                    <PageTitle title="Logs | SES WDF" />
                    {/*<Logs />*/}
                  </>
                }
              />
            </Routes>
          </DefaultLayout>
        </PrivateRoute>
      } />
    </Routes>
  );
}

export default App;
