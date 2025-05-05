// import { useEffect, useState } from "react";
// import Breadcrumb from "../../components/Breadcrumbs/Breadcrumb";
// import SelectGroup from "../../components/Forms/SelectGroup/SelectGroupOne";
// import { useNavigate, useParams } from "react-router-dom";
// import FirstScreen from "./emulators/FirstScreen";
// import SecondScreen from "./emulators/SecondScreen";
// import ThirdScreen from "./emulators/ThirdScreen";
// import apiService from "../../service/ApiService";
// import { CarrierMap } from "../../types/carrier-map";
// import { Logo } from "../../types/logo";
// import { Option } from "../../types/option";
// import { SmartWatch } from "../../types/smart-watch";
// import { ApParam } from "../../types/ap-param";
// import { Style } from "../../types/style";

// const CarrierConfig = () => {

//     const navigate = useNavigate();
//     const { id, carrierId } = useParams<{ id: string, carrierId: string }>();

//     const [formState, setFormState] = useState({
//         selectedFonts: "",
//         selectedHeaderSize: "",
//         selectedContentSize: "",
//         selectedButtonType: "",
//         selectedButtoncolor: "",
//         selectedTextButtoncolor: "",
//         selectedTitleButtonsize: "",
//         selectedCancelcolor: "",
//         selectedCancelTextcolor: "",
//         selectedCancelTitlesize: "",
//         selectedTextfieldType: "",
//         selectedLabelsize: "",
//         selectedTextfieldsize: "",
//         selectedHintSize: "",
//         selectedSmartWatch: "",
//         selectedLogo: "",
//         description: "",
//         selectedLogoUrl: "",
//     });

//     const updateFormState = (field: string, value: string) => {
//         setFormState((prev) => ({
//             ...prev,
//             [field]: value,
//         }));
//     };

//     const [stepScreen, setStepScreen] = useState<number>(0);

//     const [data, setData] = useState<CarrierMap>();

//     const [logos, setLogos] = useState<Option[]>([]);
//     const [listSmartWatch, setListSmartWatch] = useState<Option[]>([]);
//     const [dataButton, setDataButton] = useState<Option[]>([]);
//     const [dataColor, setDataColor] = useState<Option[]>([]);
//     const [dataSize, setDataSize] = useState<Option[]>([]);

//     // setup fonts
//     const [fonts, setFonts] = useState<Option[]>([]);
//     useEffect(() => {
//         fetch("data.json") // Đọc từ thư mục public
//             .then((res) => res.json())
//             .then((data) => {
//                 const fontsArray = Object.keys(data).map((key) => ({
//                     value: data[key].value,
//                     label: data[key].label,
//                 }));
//                 setFonts(fontsArray);
//             })
//             .catch((error) => console.error("Lỗi khi tải fonts:", error));
//     }, []);
//     // ----

//     useEffect(() => {
//         const fetchSmartWatch = async () => {
//             const result = await apiService({ url: "/listSmartWatch" });
//             const data: SmartWatch[] = [];
//             setListSmartWatch([]);
//             if (result) {
//                 data.push(...result);
//                 for (let i = 0; i < data.length; i++) {
//                     setListSmartWatch((prev) => [
//                         ...prev,
//                         {
//                             value: data[i].id.toString(),
//                             label: data[i].name,
//                             image: data[i].logoUrl,
//                         },
//                     ]);
//                 }
//             }
//         };

//         fetchSmartWatch();
//     }, []);

//     useEffect(() => {
//         if (id) {
//             const fetchCarrier = async () => {
//                 const result = await apiService({ url: `/getCarrierMappings/${id}` });
//                 if (result) {
//                     setData(result);
//                     updateFormState("selectedLogo", result['logoId'].toString());
//                     updateFormState("selectedSmartWatch", result['smartWatchId'].toString());
//                     updateFormState("description", result['description']);

//                     const style: Style = JSON.parse(result['style']);
//                     updateFormState("selectedFonts", style.font);
//                     updateFormState("selectedHeaderSize", style.headerSize);
//                     updateFormState("selectedContentSize", style.contentSize);
//                     updateFormState("selectedButtonType", style.buttonRadius);
//                     updateFormState("selectedButtoncolor", style.buttonColor);
//                     updateFormState("selectedTextButtoncolor", style.textButtonColor);
//                     updateFormState("selectedTitleButtonsize", style.textButtonSize);
//                     updateFormState("selectedCancelcolor", style.cancelButtonColor);
//                     updateFormState("selectedCancelTextcolor", style.cancelTextColor);
//                     updateFormState("selectedCancelTitlesize", style.cancelTextSize);
//                     updateFormState("selectedTextfieldType", style.textFieldRadius);
//                     updateFormState("selectedTextfieldsize", style.textFieldSize);
//                     updateFormState("selectedLabelsize", style.labelSize);
//                     updateFormState("selectedHintSize", style.hintSize);
//                 };
//             };

//             fetchCarrier();
//         }
//     }, []);

//     useEffect(() => {
//         const fetchLogo = async () => {
//             const result = await apiService({ url: "/listLogos?type=0" });
//             const data: Logo[] = [];
//             setLogos([]);
//             if (result) {
//                 data.push(...result);
//                 for (let i = 0; i < data.length; i++) {
//                     setLogos((prev) => [
//                         ...prev,
//                         {
//                             value: data[i].id.toString(),
//                             label: data[i].name,
//                             image: data[i].data,
//                         },
//                     ]);
//                 }
//             }
//         };

//         fetchLogo();
//     }, []);

//     useEffect(() => {
//         const fetchLogo = async () => {
//             const result = await apiService({ url: "/findAllApParamByType/button" });
//             const data: ApParam[] = [];
//             setDataButton([]);
//             if (result) {
//                 data.push(...result);
//                 for (let i = 0; i < data.length; i++) {
//                     setDataButton((prev) => [
//                         ...prev,
//                         {
//                             value: data[i].value,
//                             label: data[i].name,
//                         },
//                     ]);
//                 }
//             }
//         };

//         fetchLogo();
//     }, []);

//     useEffect(() => {
//         const fetchLogo = async () => {
//             const result = await apiService({ url: "/findAllApParamByType/color" });
//             const data: ApParam[] = [];
//             setDataColor([]);
//             if (result) {
//                 data.push(...result);
//                 for (let i = 0; i < data.length; i++) {
//                     setDataColor((prev) => [
//                         ...prev,
//                         {
//                             value: data[i].value,
//                             label: data[i].name,
//                             hexColor: `#${data[i].value}`,
//                         },
//                     ]);
//                 }
//             }
//         };

//         fetchLogo();
//     }, []);

//     useEffect(() => {
//         const fetchLogo = async () => {
//             const result = await apiService({ url: "/findAllApParamByType/size" });
//             const data: ApParam[] = [];
//             setDataSize([]);
//             if (result) {
//                 data.push(...result);
//                 for (let i = 0; i < data.length; i++) {
//                     setDataSize((prev) => [
//                         ...prev,
//                         {
//                             value: data[i].value,
//                             label: data[i].name,
//                         },
//                     ]);
//                 }
//             }
//         };

//         fetchLogo();
//     }, []);

//     useEffect(() => {
//         updateFormState("selectedLogoUrl", logos.find((item) => item.value === formState.selectedLogo)?.image || "");
//     }, [logos, formState.selectedLogo]);


//     const onSubmit = async (e: React.FormEvent) => {
//         e.preventDefault();
//         // if (!validateForm()) return;

//         const styleObject: Style = {
//             font: formState.selectedFonts,
//             headerSize: formState.selectedHeaderSize,
//             contentSize: formState.selectedContentSize,
//             buttonRadius: formState.selectedButtonType,
//             buttonColor: formState.selectedButtoncolor,
//             textButtonColor: formState.selectedTextButtoncolor,
//             textButtonSize: formState.selectedTitleButtonsize,
//             cancelButtonColor: formState.selectedCancelcolor,
//             cancelTextColor: formState.selectedCancelTextcolor,
//             cancelTextSize: formState.selectedCancelTitlesize,
//             textFieldRadius: formState.selectedTextfieldType,
//             textFieldSize: formState.selectedTextfieldsize,
//             labelSize: formState.selectedLabelsize,
//             hintSize: formState.selectedHintSize,
//             logoUrl: formState.selectedLogoUrl,
//         };

//         let data: any = {
//             "carrierId": carrierId,
//             "smartWatchId": Number.parseInt(formState.selectedSmartWatch),
//             "status": 0,
//             "style": JSON.stringify(styleObject, null, 2),
//             "description": formState.description,
//             "logoId": Number.parseInt(formState.selectedLogo),
//         };

//         if (id != null) {
//             data["id"] = id;
//         }

//         const result = await apiService({ method: data["id"] != null ? "PUT" : "POST", url: "/saveCarrierMappings", data: data });

//         if (result) navigate(-1);
//     };

//     const goToNextStep = () => {
//         setStepScreen((prevStep) => prevStep + 1);
//     };
//     const goBackStep = () => {
//         setStepScreen((prevStep) => prevStep - 1);
//     };


//     return (
//         <>
//             <Breadcrumb pageName="Carriers Form" />

//             <div className="grid grid-cols-1 gap-9 sm:grid-cols-2">
//                 <div className="flex flex-col gap-9">
//                     {/* <!-- Contact Form --> */}
//                     <div className="rounded-sm border border-stroke bg-white shadow-default dark:border-strokedark dark:bg-boxdark">
//                         <div className="border-b border-stroke py-4 px-6.5 dark:border-strokedark">
//                             <h3 className="font-medium text-black dark:text-white">
//                                 {id ? "Update Properties" : "Add Properties"}
//                             </h3>
//                         </div>
//                         <form onSubmit={onSubmit}>
//                             <div className="p-6.5">
//                                 <div className="flex flex-col xl:gap-6 xl:flex-row mb-4.5">
//                                     <div className="w-full xl:w-1/2">
//                                         <SelectGroup
//                                             defaultValue={formState.selectedSmartWatch}
//                                             title="SmartWatch"
//                                             options={listSmartWatch}
//                                             onChange={(value) => {
//                                                 updateFormState("selectedSmartWatch", value);
//                                                 // if (errors.selectedLogo) {
//                                                 //     setErrors((prev) => ({ ...prev, selectedLogo: undefined }));
//                                                 // }
//                                             }}
//                                         />
//                                     </div>
//                                     <div className="w-full xl:w-1/2">
//                                         <SelectGroup
//                                             defaultValue={formState.selectedLogo}
//                                             title="Logo"
//                                             options={logos}
//                                             onChange={(value) => {
//                                                 updateFormState("selectedLogo", value);
//                                                 updateFormState("selectedLogoUrl", logos.find((item) => item.value === value)?.image || "");
//                                                 // if (errors.selectedLogo) {
//                                                 //     setErrors((prev) => ({ ...prev, selectedLogo: undefined }));
//                                                 // }
//                                             }}
//                                         />
//                                     </div>
//                                 </div>
//                                 <div className="mb-4.5 w-full">
//                                     <label className="mb-2.5 block text-black dark:text-white">
//                                         Description
//                                     </label>
//                                     <input
//                                         value={formState.description}
//                                         onChange={(e) => {
//                                             updateFormState("description", e.target.value);
//                                             // if (errors.name) {
//                                             //     setErrors((prev) => ({ ...prev, name: undefined }));
//                                             // }
//                                         }}
//                                         type="text"
//                                         placeholder="Enter description"
//                                         className="w-full rounded border-[1.5px] border-stroke bg-transparent py-3 px-5 text-black outline-none transition focus:border-primary active:border-primary disabled:cursor-default disabled:bg-whiter dark:border-form-strokedark dark:bg-form-input dark:text-white dark:focus:border-primary"
//                                     />
//                                     {/* {errors.name && <p className="text-red-500 text-sm mt-1">{errors.name}</p>} */}
//                                 </div>
//                                 <div className="mb-4.5">
//                                     <SelectGroup
//                                         title="Fonts"
//                                         defaultValue={formState.selectedFonts}
//                                         options={fonts}
//                                         onChange={(value) => updateFormState("selectedFonts", value)}
//                                     />
//                                 </div>

//                                 <div className="flex flex-col xl:gap-6 xl:flex-row mb-4.5">
//                                     <div className="w-full xl:w-1/2">
//                                         <SelectGroup
//                                             title="Header size"
//                                             isSearchable={true}
//                                             defaultValue={formState.selectedHeaderSize}
//                                             options={dataSize}
//                                             onChange={(value) => updateFormState("selectedHeaderSize", value)}
//                                         />
//                                     </div>

//                                     <div className="w-full xl:w-1/2">
//                                         <SelectGroup
//                                             title="Content size"
//                                             isSearchable={true}
//                                             defaultValue={formState.selectedContentSize}
//                                             options={dataSize}
//                                             onChange={(value) => updateFormState("selectedContentSize", value)}
//                                         />
//                                     </div>
//                                 </div>
//                                 <div className="mb-4.5">
//                                     <SelectGroup
//                                         title="Button Type"
//                                         defaultValue={formState.selectedButtonType}
//                                         options={dataButton}
//                                         onChange={(value) => updateFormState("selectedButtonType", value)}
//                                     />
//                                 </div>
//                                 <div className="flex flex-col xl:gap-6 xl:flex-row mb-4.5">
//                                     <div className="w-full xl:w-1/3">
//                                         <SelectGroup
//                                             title="Button color"
//                                             isSearchable={true}
//                                             defaultValue={formState.selectedButtoncolor}
//                                             options={dataColor}
//                                             onChange={(value) => updateFormState("selectedButtoncolor", value)}
//                                         />
//                                     </div>
//                                     <div className="w-full xl:w-1/3">
//                                         <SelectGroup
//                                             title="Text button color"
//                                             isSearchable={true}
//                                             defaultValue={formState.selectedTextButtoncolor}
//                                             options={dataColor}
//                                             onChange={(value) => updateFormState("selectedTextButtoncolor", value)}
//                                         />
//                                     </div>
//                                     <div className="w-full xl:w-1/3">
//                                         <SelectGroup
//                                             title="Text button size"
//                                             isSearchable={true}
//                                             defaultValue={formState.selectedTitleButtonsize}
//                                             options={dataSize}
//                                             onChange={(value) => updateFormState("selectedTitleButtonsize", value)}
//                                         />
//                                     </div>
//                                 </div>
//                                 <div className="flex flex-col xl:gap-6 xl:flex-row mb-4.5">
//                                     <div className="w-full xl:w-1/3">
//                                         <SelectGroup
//                                             title="Cancel color"
//                                             isSearchable={true}
//                                             defaultValue={formState.selectedCancelcolor}
//                                             options={dataColor}
//                                             onChange={(value) => updateFormState("selectedCancelcolor", value)}
//                                         />
//                                     </div>
//                                     <div className="w-full xl:w-1/3">
//                                         <SelectGroup
//                                             title="Cancel text color"
//                                             isSearchable={true}
//                                             defaultValue={formState.selectedCancelTextcolor}
//                                             options={dataColor}
//                                             onChange={(value) => updateFormState("selectedCancelTextcolor", value)}
//                                         />
//                                     </div>
//                                     <div className="w-full xl:w-1/3">
//                                         <SelectGroup
//                                             title="Cancel text size"
//                                             isSearchable={true}
//                                             defaultValue={formState.selectedCancelTitlesize}
//                                             options={dataSize}
//                                             onChange={(value) => updateFormState("selectedCancelTitlesize", value)}
//                                         />
//                                     </div>
//                                 </div>
//                                 <div className="mb-4.5">
//                                     <SelectGroup
//                                         title="Textfield Type"
//                                         defaultValue={formState.selectedTextfieldType}
//                                         options={dataButton}
//                                         onChange={(value) => updateFormState("selectedTextfieldType", value)}
//                                     />
//                                 </div>

//                                 <div className="flex flex-col xl:gap-6 xl:flex-row mb-6.5">
//                                     <div className="w-full xl:w-1/3">
//                                         <SelectGroup
//                                             title="Label size"
//                                             isSearchable={true}
//                                             defaultValue={formState.selectedLabelsize}
//                                             options={dataSize}
//                                             onChange={(value) => updateFormState("selectedLabelsize", value)}
//                                         />
//                                     </div>

//                                     <div className="w-full xl:w-1/3">
//                                         <SelectGroup
//                                             title="Textfield size"
//                                             isSearchable={true}
//                                             defaultValue={formState.selectedTextfieldsize}
//                                             options={dataSize}
//                                             onChange={(value) => updateFormState("selectedTextfieldsize", value)}
//                                         />
//                                     </div>

//                                     <div className="w-full xl:w-1/3">
//                                         <SelectGroup
//                                             title="Hint size"
//                                             isSearchable={true}
//                                             defaultValue={formState.selectedHintSize}
//                                             options={dataSize}
//                                             onChange={(value) => updateFormState("selectedHintSize", value)}
//                                         />
//                                     </div>
//                                 </div>

//                                 <div className="flex gap-4">
//                                     <button type="button" onClick={() => navigate(-1)} className="flex w-full justify-center items-center rounded-md border border-primary p-3 font-medium text-primary hover:bg-opacity-90">
//                                         Cancel
//                                     </button>
//                                     <button type="submit" className="flex w-full justify-center items-center rounded bg-primary p-3 font-medium text-gray hover:bg-opacity-90">
//                                         Save
//                                     </button>
//                                 </div>
//                             </div>
//                         </form>
//                     </div>
//                 </div>

//                 <div className="flex flex-col gap-9 items-center">
//                     <div className="bg-phone fixed p-3">
//                         <div className="emulator-scroll bg-white dark:bg-boxdark h-full p-6 w-full rounded-6xl">
//                             {stepScreen === 0 && <FirstScreen goToNextStep={goToNextStep} formState={formState} />}
//                             {stepScreen === 1 && <SecondScreen goToNextStep={goToNextStep} goBackStep={goBackStep} formState={formState} />}
//                             {stepScreen === 2 && <ThirdScreen goBackStep={goBackStep} formState={formState} />}
//                         </div>
//                     </div>
//                 </div>
//             </div>
//         </>
//     );
// };

// export default CarrierConfig;
