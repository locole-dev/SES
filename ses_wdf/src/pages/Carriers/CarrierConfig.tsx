import { useEffect, useRef, useState } from "react";
import Breadcrumb from "../../components/Breadcrumbs/Breadcrumb";
import SelectGroup from "../../components/Forms/SelectGroup/SelectGroupOne";
import { useNavigate, useParams } from "react-router-dom";
import FirstScreen from "./emulators/FirstScreen";
import SecondScreen from "./emulators/SecondScreen";
import ThirdScreen from "./emulators/ThirdScreen";
import apiService from "../../service/ApiService";
import { CarrierMap } from "../../types/carrier-map";
import { Logo } from "../../types/logo";
import { Option } from "../../types/option";
import { SmartWatch } from "../../types/smart-watch";
import { ApParam } from "../../types/ap-param";
import { StyleData } from "../../types/style";
import InputEditor from "../../components/Forms/InputEditor/InputEditor";
import ConfirmBox from "../../components/ConfirmBox";
import Switcher from "../../components/Switchers/SwitcherThree";
import { Trash } from "../../components/Icons";
import CustomInputEditor from "../../components/Forms/InputEditor/CustomInputEditor";

const CarrierConfig = () => {

    const navigate = useNavigate();
    const { id, carrierId } = useParams<{ id: string, carrierId: string }>();
    const [status, setStatus] = useState<boolean>(true);
    const [styleData, setStyleData] = useState<StyleData>({ lablel: {}, header: {}, content: {}, hint: {}, button: {}, cancelButton: {}, textField: {}, error: {} });
    const [formState, setFormState] = useState({
        selectedSmartWatch: "",
        selectedLogo: "",
        description: "",
        dictionary:"",
        selectedLogoUrl: "",
        backgroundId: "",
        backgroundUrl: "",
    });
   const [dictionaryData, setDictionaryData] = useState<any[]>([]);
    const updateFormState = (field: string, value: string) => {
        setFormState((prev) => ({
            ...prev,
            [field]: value,
        }));
    };

    const [stepScreen, setStepScreen] = useState<number>(0);

    const [data, setData] = useState<CarrierMap>();

    const [logos, setLogos] = useState<Option[]>([]);
    const [listSmartWatch, setListSmartWatch] = useState<any[]>([]);
    const [listBackground, setListBackground] = useState<Option[]>([]);
    const [dataButton, setDataButton] = useState<Option[]>([]);
    const [dataColor, setDataColor] = useState<Option[]>([]);
    const [dataSize, setDataSize] = useState<Option[]>([]);

    // setup fonts
    const [fonts, setFonts] = useState<any[]>([]);
    const [fontWeight, setFontWeight] = useState<Option[]>([]);
    const [fontStyle, setFontStyle] = useState<Option[]>([]);
    useEffect(() => {
        fetch("/gui/data.json")
            .then((res) => res.json())
            .then((data) => {
                if (data.fonts) {
                    const fontsArray = Object.keys(data.fonts).map((key) => ({
                        value: data.fonts[key].value,
                        label: data.fonts[key].label,
                    }));
                    setFonts(fontsArray);
                }

                if (data.fontWeight) {
                    const fontWeightArray = Object.keys(data.fontWeight).map((key) => ({
                        value: data.fontWeight[key].value,
                        label: data.fontWeight[key].label,
                    }));
                    setFontWeight(fontWeightArray);
                }

                if (data.fontStyle) {
                    const fontStyleArray = Object.keys(data.fontStyle).map((key) => ({
                        value: data.fontStyle[key].value,
                        label: data.fontStyle[key].label,
                    }));
                    setFontStyle(fontStyleArray);
                }
            })
            .catch((error) => console.error("Lỗi khi tải dữ liệu font:", error));
    }, []);
    // ----

    useEffect(() => {
        const fetchAll = async () => {
            try {
                const [carrierRes, mappingRes, smartWatchRes] = await Promise.all([
                    id != null ? apiService({ url: `/getCarrierMappings/${id}` }) : null,
                    apiService({ url: `/listCarrierMappingsByCarrier/${carrierId}` }),
                    apiService({ url: "/listSmartWatch" })
                ]);
                const listCM: CarrierMap[] = [];

                if (carrierRes) {
                    setData(carrierRes);
                    updateFormState("selectedLogo", carrierRes['logoId'].toString());
                    updateFormState("selectedSmartWatch", carrierRes['smartWatchId'].toString());
                    if (carrierRes['backgroundId'] != null) {
                        updateFormState("backgroundId", carrierRes['backgroundId'].toString());
                        updateFormState("backgroundUrl", carrierRes['backgroundUrl'].toString());
                    }
                    updateFormState("description", carrierRes['description']);
                    setStyleData(JSON.parse(carrierRes['style']));
                    setStatus(carrierRes['status'] === 0 ? true : false);
                }

                if (mappingRes) {
                    listCM.push(...mappingRes);
                }

                if (smartWatchRes) {
                    const data: SmartWatch[] = [];
                    setListSmartWatch([]);
                    if (smartWatchRes) {
                        data.push(...smartWatchRes);
                        for (let i = 0; i < data.length; i++) {
                            setListSmartWatch((prev) => [
                                ...prev,
                                {
                                    value: data[i].id.toString(),
                                    label: data[i].name,
                                    image: data[i].logoUrl,
                                    dictionaryKey: data[i].dictionaryKey,
                                    disbled: (carrierRes != null && carrierRes['smartWatchId'] != undefined && carrierRes['smartWatchId'] != null && carrierRes['smartWatchId'] == data[i].id) ? false : listCM.find((item) => item.smartWatchId === data[i].id) ? true : false,
                                },
                            ]);
                        }
                    }

                }
            } catch (error) {

            }
        };
        fetchAll();
    }, [id]);

    useEffect(() => {
        if (!id) {
            if (carrierId) {
                const fetchCarrier = async () => {
                    const result = await apiService({ url: `/getCarrier/${carrierId}` });
                    if (result) {
                        updateFormState("selectedLogo", result.logoId.toString());
                    }
                };

                fetchCarrier();
            }

            fetch("/gui/data.json")
                .then((res) => res.json())
                .then((data) => {
                    setStyleData(data.defaultStyle);
                })
                .catch((err) => {
                    console.error('Failed to load style:', err);
                });

            updateFormState("description", 'default');
        }
    }, []);

    useEffect(() => {
        const fetchLogo = async () => {
            const result = await apiService({ url: "/listLogos?type=0" });
            const data: Logo[] = [];
            setLogos([]);
            if (result) {
                data.push(...result);
                for (let i = 0; i < data.length; i++) {
                    setLogos((prev) => [
                        ...prev,
                        {
                            value: data[i].id.toString(),
                            label: '',
                            image: data[i].data,
                        },
                    ]);
                }
            }
        };

        fetchLogo();
    }, []);

    useEffect(() => {
        const fetchBackground = async () => {
            const result = await apiService({ url: "/listLogos?type=2" });
            const data: Logo[] = [];
            setListBackground([]);
            if (result) {
                data.push(...result);
                for (let i = 0; i < data.length; i++) {
                    setListBackground((prev) => [
                        ...prev,
                        {
                            value: data[i].id.toString(),
                            label: '',
                            image: data[i].data,
                        },
                    ]);
                }
            }
        };

        fetchBackground();
    }, []);

    useEffect(() => {
        const fetchLogo = async () => {
            const result = await apiService({ url: "/findAllApParamByType/button" });
            const data: ApParam[] = [];
            setDataButton([]);
            if (result) {
                data.push(...result);
                for (let i = 0; i < data.length; i++) {
                    setDataButton((prev) => [
                        ...prev,
                        {
                            value: data[i].value,
                            label: data[i].name,
                        },
                    ]);
                }
            }
        };

        fetchLogo();
    }, []);

    useEffect(() => {
        const fetchLogo = async () => {
            const result = await apiService({ url: "/findAllApParamByType/color" });
            const data: ApParam[] = [];
            setDataColor([]);
            if (result) {
                data.push(...result);
                for (let i = 0; i < data.length; i++) {
                    setDataColor((prev) => [
                        ...prev,
                        {
                            value: data[i].value,
                            label: data[i].name,
                            hexColor: `#${data[i].value}`,
                        },
                    ]);
                }
            }
        };

        fetchLogo();
    }, []);

    useEffect(() => {
        const fetchLogo = async () => {
            const result = await apiService({ url: "/findAllApParamByType/size" });
            const data: ApParam[] = [];
            setDataSize([]);
            if (result) {
                data.push(...result);
                for (let i = 0; i < data.length; i++) {
                    setDataSize((prev) => [
                        ...prev,
                        {
                            value: data[i].value,
                            label: data[i].name,
                        },
                    ]);
                }
            }
        };

        fetchLogo();
    }, []);
    useEffect(() => {
        if (formState.selectedSmartWatch !== '' ){
            const selectedSmartWatch = listSmartWatch.find((item) => item.value === formState.selectedSmartWatch)?.dictionaryKey || null;
            if(selectedSmartWatch) {
                getDictionaryDataBySmartWatchKey(selectedSmartWatch);
            }
        }
    }, [formState.selectedSmartWatch]);
    const getDictionaryDataBySmartWatchKey = async  (smartWatchKey: string) => {
        const result = await apiService({ url: `/getDictionaryBySmartWatchKey/${smartWatchKey}` });
        setDictionaryData(result);
    }
    useEffect(() => {
        updateFormState("selectedLogoUrl", logos.find((item) => item.value === formState.selectedLogo)?.image || "");
    }, [logos, formState.selectedLogo]);

    const onSubmit = async (e: React.FormEvent) => {

        e.preventDefault();
        // if (!validateForm()) return;

        let data: any = {
            "carrierId": Number.parseInt(carrierId!),
            "smartWatchId": Number.parseInt(formState.selectedSmartWatch),
            "status": status ? 0 : 1,
            "style": JSON.stringify(styleData),
            "description": formState.description,
            "logoId": Number.parseInt(formState.selectedLogo)
        };

        if (id != null) {
            data["id"] = id;
        }

        if (formState.backgroundId != null && formState.backgroundId != "") {
            data["backgroundId"] = Number.parseInt(formState.backgroundId);
        }

        console.log(data);


        const result = await apiService({ method: data["id"] != null ? "PUT" : "POST", url: "/saveCarrierMappings", data: data });

        if (result) navigate(-1);
    };

    const goToNextStep = () => {
        setStepScreen((prevStep) => prevStep + 1);
    };
    const goBackStep = () => {
        setStepScreen((prevStep) => prevStep - 1);
    };


    // setup dialog
    const [showConfirm, setShowConfirm] = useState<string | null>(null);
    const [position, setPosition] = useState<{ top: number; left: number } | null>(null);
    const buttonRef = useRef<HTMLButtonElement | null>(null);


    const handleShowConfirm = (id: string, event: React.MouseEvent) => {
        const rect = (event.target as HTMLElement).getBoundingClientRect();
        setPosition({
            top: rect.top + 32, // Đẩy lên trên
            left: rect.left - 40, // Căn giữa
        });
        setShowConfirm(id);
    };

    const handleDelete = async () => {
        setShowConfirm(null);
        const data = {
            "id": showConfirm,
        }
        const result = await apiService({ method: "DELETE", url: `/saveCarrierMappings`, data: data });

        if (result) navigate(-1);
    };
    // ----


    return (
        <>
            <Breadcrumb pageName="Carriers Form" />

            <div className="grid grid-cols-1 gap-9 sm:grid-cols-2">
                <div className="flex flex-col gap-9">
                    {/* <!-- Contact Form --> */}
                    <div className="rounded-md border border-stroke bg-white shadow-default dark:border-strokedark dark:bg-boxdark">
                        <div className="border-b border-stroke py-4 px-6.5 dark:border-strokedark">
                            {showConfirm !== null && position && (
                                <ConfirmBox position={position} onClose={() => setShowConfirm(null)} onConfirm={handleDelete} />
                            )}
                            <div className="flex">
                                <h3 className="grow font-medium text-black dark:text-white">
                                    {id ? "Update Properties" : "Add Properties"}
                                </h3>
                                <div className="flex items-center gap-2">
                                    <span>Status</span>
                                    <Switcher enabled={status} setEnabled={setStatus} />
                                    <div className="px-1">
                                        <span className="border h-3/5"></span>
                                    </div>
                                    <button ref={buttonRef}
                                        onClick={(e) => handleShowConfirm(id ?? "", e)}>
                                        <Trash fill="red" />
                                    </button>
                                </div>
                            </div>
                        </div>
                        <form onSubmit={onSubmit}>
                            <div className="p-6.5">
                                <div className="flex flex-col xl:gap-6 xl:flex-row mb-4.5">
                                    <div className="w-full xl:w-1/2">
                                        <SelectGroup
                                            defaultValue={formState.selectedSmartWatch}
                                            title="SmartWatch"
                                            options={listSmartWatch}
                                            onChange={(value) => {
                                                updateFormState("selectedSmartWatch", value);
                                                const selectedSmartWatch = listSmartWatch.find((item) => item.value === value)?.dictionaryKey || null;
                                                if(selectedSmartWatch) {
                                                    getDictionaryDataBySmartWatchKey(selectedSmartWatch);
                                                }
                                                // if (errors.selectedLogo) {
                                                //     setErrors((prev) => ({ ...prev, selectedLogo: undefined }));
                                                // }
                                            }}
                                        />
                                    </div>
                                    <div className="w-full xl:w-1/2">
                                        <SelectGroup
                                            defaultValue={formState.selectedLogo}
                                            title="Logo"
                                            options={logos}
                                            onChange={(value) => {
                                                updateFormState("selectedLogo", value);
                                                updateFormState("selectedLogoUrl", logos.find((item) => item.value === value)?.image || "");
                                                // if (errors.selectedLogo) {
                                                //     setErrors((prev) => ({ ...prev, selectedLogo: undefined }));
                                                // }
                                            }}
                                        />
                                    </div>
                                </div>
                                <div className="mb-4.5 w-full">
                                    <label className="mb-2.5 block text-black dark:text-white">
                                        Description
                                    </label>
                                    <input
                                        value={formState.description}
                                        onChange={(e) => {
                                            updateFormState("description", e.target.value);
                                            // if (errors.name) {
                                            //     setErrors((prev) => ({ ...prev, name: undefined }));
                                            // }
                                        }}
                                        type="text"
                                        placeholder="Enter description"
                                        className="w-full rounded border-[1.5px] border-stone-300 bg-transparent py-3 px-5 text-black outline-none transition focus:border-primary active:border-primary disabled:cursor-default disabled:bg-whiter dark:border-form-strokedark dark:bg-form-input dark:text-white dark:focus:border-primary"
                                    />
                                    {/* {errors.name && <p className="text-red-500 text-sm mt-1">{errors.name}</p>} */}
                                </div>
                                <div className="flex flex-col xl:gap-6 xl:flex-row mb-4.5">
                                    <div className="w-full xl:w-1/2">
                                        <CustomInputEditor
                                            title="Background"
                                            defaultValue={formState.backgroundId}
                                            data={listBackground}
                                            onChange={(value) => {
                                                updateFormState("backgroundId", value);
                                                updateFormState("backgroundUrl", listBackground.find((item) => item.value === value)?.image || "");
                                            }}
                                        />
                                    </div>
                                    <div className="w-full xl:w-1/2">
                                        <SelectGroup
                                            title="Fonts"
                                            defaultValue={styleData.font}
                                            options={fonts}
                                            onChange={(value) => {
                                                setStyleData(prev => ({
                                                    ...prev,
                                                    font: value
                                                }));
                                            }}
                                        />
                                    </div>
                                </div>

                                <div className="flex flex-col xl:gap-6 xl:flex-row mb-4.5">
                                    <div className="w-full xl:w-1/2">
                                        <InputEditor title="Label Config" isText={true} dataButton={dataButton} dataColor={dataColor} dataSize={dataSize} formState={styleData.lablel ?? {}} fontStyle={fontStyle} fontWeight={fontWeight} onChange={(value) => {
                                            setStyleData(prev => ({
                                                ...prev,
                                                lablel: {
                                                    ...prev?.lablel,
                                                    fontWeight: value.fontWeight,
                                                    fontStyle: value.fontStyle,
                                                    size: value.size,
                                                    color: value.color,
                                                    type: value.type,
                                                    background: value.background,
                                                    borderColor: value.borderColor,
                                                }
                                            }));
                                        }} /></div>
                                    <div className="w-full xl:w-1/2">
                                        <InputEditor title="Hint Config" isText={true} dataButton={dataButton} dataColor={dataColor} dataSize={dataSize} formState={styleData.hint ?? {}} fontStyle={fontStyle} fontWeight={fontWeight} onChange={(value) => {
                                            setStyleData(prev => ({
                                                ...prev,
                                                hint: {
                                                    ...prev?.hint,
                                                    fontWeight: value.fontWeight,
                                                    fontStyle: value.fontStyle,
                                                    size: value.size,
                                                    color: value.color,
                                                    type: value.type,
                                                    background: value.background,
                                                    borderColor: value.borderColor,
                                                }
                                            }));
                                        }} /></div>

                                </div>
                                <div className="flex flex-col xl:gap-6 xl:flex-row mb-4.5">
                                    <div className="w-full xl:w-1/2">
                                        <InputEditor title="Header Config" isText={true} dataButton={dataButton} dataColor={dataColor} dataSize={dataSize} formState={styleData.header ?? {}} fontStyle={fontStyle} fontWeight={fontWeight} onChange={(value) => {
                                            setStyleData(prev => ({
                                                ...prev,
                                                header: {
                                                    ...prev?.header,
                                                    fontWeight: value.fontWeight,
                                                    fontStyle: value.fontStyle,
                                                    size: value.size,
                                                    color: value.color,
                                                    type: value.type,
                                                    background: value.background,
                                                    borderColor: value.borderColor,
                                                }
                                            }));
                                        }} /></div>
                                    <div className="w-full xl:w-1/2">
                                        <InputEditor title="Content Config" isText={true} dataButton={dataButton} dataColor={dataColor} dataSize={dataSize} formState={styleData.content ?? {}} fontStyle={fontStyle} fontWeight={fontWeight} onChange={(value) => {
                                            setStyleData(prev => ({
                                                ...prev,
                                                content: {
                                                    ...prev?.content,
                                                    fontWeight: value.fontWeight,
                                                    fontStyle: value.fontStyle,
                                                    size: value.size,
                                                    color: value.color,
                                                    type: value.type,
                                                    background: value.background,
                                                    borderColor: value.borderColor,
                                                }
                                            }));
                                        }} /></div>

                                </div>
                                <div className="mb-4.5">
                                    <InputEditor title="Error Config" isText={true} dataButton={dataButton} dataColor={dataColor} dataSize={dataSize} formState={styleData.error ?? {}} fontStyle={fontStyle} fontWeight={fontWeight} onChange={(value) => {
                                        setStyleData(prev => ({
                                            ...prev,
                                            error: {
                                                ...prev?.error,
                                                fontWeight: value.fontWeight,
                                                fontStyle: value.fontStyle,
                                                size: value.size,
                                                color: value.color,
                                                type: value.type,
                                                background: value.background,
                                                borderColor: value.borderColor,
                                            }
                                        }));
                                    }} />
                                </div>
                                <div className="mb-4.5">
                                    <InputEditor title="TextField Config" isTextField={true} dataButton={dataButton} dataColor={dataColor} dataSize={dataSize} formState={styleData.textField ?? {}} fontStyle={fontStyle} fontWeight={fontWeight} onChange={(value) => {
                                        setStyleData(prev => ({
                                            ...prev,
                                            textField: {
                                                ...prev?.textField,
                                                fontWeight: value.fontWeight,
                                                fontStyle: value.fontStyle,
                                                size: value.size,
                                                color: value.color,
                                                type: value.type,
                                                background: value.background,
                                                borderColor: value.borderColor,
                                            }
                                        }));
                                    }} />
                                </div>
                                <div className="mb-4.5">
                                    <InputEditor title="Button Config" isButton={true} dataButton={dataButton} dataColor={dataColor} dataSize={dataSize} formState={styleData.button ?? {}} fontStyle={fontStyle} fontWeight={fontWeight} onChange={(value) => {
                                        setStyleData(prev => ({
                                            ...prev,
                                            button: {
                                                ...prev?.button,
                                                fontWeight: value.fontWeight,
                                                fontStyle: value.fontStyle,
                                                size: value.size,
                                                color: value.color,
                                                type: value.type,
                                                background: value.background,
                                                borderColor: value.borderColor,
                                            }
                                        }));
                                    }} />
                                </div>
                                <div className="mb-12">
                                    <InputEditor title="Cancel Config" isButton={true} dataButton={dataButton} dataColor={dataColor} dataSize={dataSize} formState={styleData.cancelButton ?? {}} fontStyle={fontStyle} fontWeight={fontWeight} onChange={(value) => {
                                        setStyleData(prev => ({
                                            ...prev,
                                            cancelButton: {
                                                ...prev?.cancelButton,
                                                fontWeight: value.fontWeight,
                                                fontStyle: value.fontStyle,
                                                size: value.size,
                                                color: value.color,
                                                type: value.type,
                                                background: value.background,
                                                borderColor: value.borderColor,
                                            }
                                        }));
                                    }} />
                                </div>

                                <div className="flex gap-4">
                                    <button type="button" onClick={() => navigate(-1)} className="flex w-full justify-center items-center rounded-md border border-primary p-3 font-medium text-primary hover:bg-opacity-90">
                                        Cancel
                                    </button>
                                    <button type="submit" className="flex w-full justify-center items-center rounded bg-primary p-3 font-medium text-gray hover:bg-opacity-90">
                                        Save
                                    </button>
                                </div>
                            </div>
                        </form>
                    </div>
                </div>

                <div className="flex flex-col gap-9 items-center">
                    <div className="bg-phone fixed bg-white dark:bg-boxdark rounded-6xl">
                        <div className="emulator-scroll h-full w-full p-2.5">
                            {stepScreen === 0 && <FirstScreen goToNextStep={goToNextStep} formState={styleData} logoUrl={formState.selectedLogoUrl} backgroundUrl={formState.backgroundUrl} dictionaryData={dictionaryData} />}
                            {stepScreen === 1 && <SecondScreen goToNextStep={goToNextStep} goBackStep={goBackStep} formState={styleData} logoUrl={formState.selectedLogoUrl} backgroundUrl={formState.backgroundUrl} dictionaryData={dictionaryData} />}
                            {stepScreen === 2 && <ThirdScreen goBackStep={goBackStep} formState={styleData} logoUrl={formState.selectedLogoUrl} backgroundUrl={formState.backgroundUrl} dictionaryData={dictionaryData}/>}
                        </div>
                    </div>
                </div>
            </div>
        </>
    );
};

export default CarrierConfig;
