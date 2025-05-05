import { useEffect, useState } from "react";
import Breadcrumb from "../../components/Breadcrumbs/Breadcrumb";
import { useNavigate, useParams } from "react-router-dom";
import apiService from "../../service/ApiService";
import { DictionaryType } from "../../types/dictionary";
import { SmartWatch } from "../../types/smart-watch";
import MultiSelectGroup from '../../components/Forms/SelectGroup/MutilSelectGroup.tsx';


const DictionaryInsert = () => {

    const navigate = useNavigate();
    const { id } = useParams<{ id: string }>();
    const [dictionary, setDictionary] = useState<DictionaryType>({ id: 0, key: "", vi: "", eng: "", smartWatchKey: [] as string[] });
    const [errors, setErrors] = useState<{ key?: string; vi?: string, eng?: string }>({});
    const [listSmartWatch, setListSmartWatch] = useState<any[]>([]);
    useEffect(() => {
        const fetchSmartWatch = async () => {
            const smartWatchRes = await apiService({
                url: "/listSmartWatch"
            })
            const data: SmartWatch[] = [];
            setListSmartWatch([ {
                value: '0',
                label: 'All',
                image: undefined,
                isDisabled: false,
                dictionaryKey: undefined

            }]);
            if (smartWatchRes) {
                data.push(...smartWatchRes);
                for (let i = 0; i < data.length; i++) {
                    setListSmartWatch((prev) => [
                        ...prev,
                        {
                            value: data[i].id.toString(),
                            label: data[i].name,
                            dictionaryKey: data[i].dictionaryKey,
                          isDisabled: data[i].isDisabled,
                        },
                    ]);
                }
            }            
        }
        if (id) {
            const fetchLogo = async () => {
                const result = await apiService({ url: `/getDictionary/${id}` });
                setDictionary(result);
            };
            fetchLogo();
        }
        fetchSmartWatch();
    }, []);

    const onSubmit = async (e: React.FormEvent) => {
        e.preventDefault();
        if (!validateForm()) return;
        let data: any = {
            "key": dictionary.key,
            "vi": dictionary.vi,
            "eng": dictionary.eng,
            "smartWatchKeys": dictionary.smartWatchKey
        };
        if (id != null) {
            data["id"] = id;
        }

        const result = await apiService({ method: data["id"] != null ? "PUT" : "POST", url: "/saveDictionary", data: data });

        if (result) {
            setErrors({});
            navigate(-1)
        }
    };

    const onBack = (e: React.FormEvent) => {
        e.preventDefault();
        navigate(-1);
    };
    const validateForm = () => {
        const newErrors: { key?: string; vi?: string, eng?: string } = {};

        if (!dictionary.key.trim()) {
            newErrors.key = "Tên không được để trống!";
        }

        if (!dictionary.vi) {
            newErrors.vi = "Trường không được để trống!";
        }

        if (!dictionary.eng) {
            newErrors.eng = "Trường không được để trống!";
        }

        setErrors(newErrors);
        return Object.keys(newErrors).length === 0;
    }

    return (
      <>
        <Breadcrumb pageName="SmartWatch Form" />

        <div className="flex flex-col">
          {/* <!-- Contact Form --> */}
          <div className="rounded-md border border-stroke bg-white shadow-default dark:border-strokedark dark:bg-boxdark">
            <div className="border-b border-stroke py-4 px-6.5 dark:border-strokedark">
              <h3 className="font-medium text-black dark:text-white">
                Insert Form
              </h3>
            </div>
            <form onSubmit={onSubmit}>
              <div className="p-6.5">
                <div className="flex items-center gap-3">
                  <div className="mb-4.5 w-full">
                    <label className="mb-2.5 block text-black dark:text-white">
                      Key
                    </label>
                    <input
                      value={dictionary.key}
                      onChange={(e) => {
                        setDictionary((prev) => ({
                          ...prev,
                          key: e.target.value,
                        }));
                        if (errors.key) {
                          setErrors((prev) => ({ ...prev, key: undefined }));
                        }
                      }}
                      type="text"
                      placeholder="Enter key"
                      className="w-full rounded border-[1.5px] border-stroke bg-transparent py-3 px-5 text-black outline-none transition focus:border-primary active:border-primary disabled:cursor-default disabled:bg-whiter dark:border-form-strokedark dark:bg-form-input dark:text-white dark:focus:border-primary"
                    />
                    {errors.key && (
                      <p className="text-red-500 text-sm mt-1">{errors.key}</p>
                    )}
                  </div>
                </div>
                <div className="w-full  gap-3 mb-4 ">
                  <MultiSelectGroup
                    title="SmartWatch"
                    options={listSmartWatch}
                    onChange={function (selectedValues: any[]): void {
                      setDictionary((prev) => ({
                        ...prev,
                        smartWatchKey: selectedValues.map((item) => item.dictionaryKey),
                      }));
                    }}
                  />
                </div>
                <div className="flex items-center gap-3">
                  <div className="mb-4.5 w-full">
                    <label className="mb-2.5 block text-black dark:text-white">
                      Vietnamese
                    </label>
                    <textarea
                      value={dictionary.vi}
                      onChange={(e) => {
                        setDictionary((prev) => ({
                          ...prev,
                          vi: e.target.value,
                        }));
                        if (errors.vi) {
                          setErrors((prev) => ({ ...prev, vi: undefined }));
                        }
                      }}
                      placeholder="Enter vietnamese value"
                      className="w-full h-40 rounded border-[1.5px] border-stroke bg-transparent py-3 px-5 text-black outline-none transition focus:border-primary active:border-primary disabled:cursor-default disabled:bg-whiter dark:border-form-strokedark dark:bg-form-input dark:text-white dark:focus:border-primary"
                    />
                    {errors.vi && (
                      <p className="text-red-500 text-sm mt-1">{errors.vi}</p>
                    )}
                  </div>
                </div>
                <div className="mb-4 flex items-center gap-3">
                  <div className="mb-4.5 w-full">
                    <label className="mb-2.5 block text-black dark:text-white">
                      English
                    </label>
                    <textarea
                      value={dictionary.eng}
                      onChange={(e) => {
                        setDictionary((prev) => ({
                          ...prev,
                          eng: e.target.value,
                        }));
                        if (errors.eng) {
                          setErrors((prev) => ({ ...prev, eng: undefined }));
                        }
                      }}
                      placeholder="Enter english value"
                      className="w-full h-40 rounded border-[1.5px] border-stroke bg-transparent py-3 px-5 text-black outline-none transition focus:border-primary active:border-primary disabled:cursor-default disabled:bg-whiter dark:border-form-strokedark dark:bg-form-input dark:text-white dark:focus:border-primary"
                    />
                    {errors.eng && (
                      <p className="text-red-500 text-sm mt-1">{errors.eng}</p>
                    )}
                  </div>
                </div>

                <div className="flex justify-end gap-4.5">
                  {id && (
                    <button
                      onClick={() => {
                        navigate(-1);
                      }}
                      className="flex justify-center rounded border border-stroke py-2 px-6 font-medium text-black hover:shadow-1 dark:border-strokedark dark:text-white"
                      type="button"
                    >
                      Cancel
                    </button>
                  )}
                  <button
                    className="flex justify-center rounded bg-primary py-2 px-6 font-medium text-gray hover:bg-opacity-90"
                    type="submit"
                  >
                    Save
                  </button>
                </div>
              </div>
            </form>
          </div>
        </div>
      </>
    );
};

export default DictionaryInsert;
