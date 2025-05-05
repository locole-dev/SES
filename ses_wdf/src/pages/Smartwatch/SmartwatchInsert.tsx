import { useEffect, useState } from "react";
import Breadcrumb from "../../components/Breadcrumbs/Breadcrumb";
import { useNavigate, useParams } from "react-router-dom";
import { Option } from "../../types/option";
import CheckboxFive from "../../components/Checkboxes/CheckboxFive";
import SelectGroup from "../../components/Forms/SelectGroup/SelectGroupOne";
import apiService from "../../service/ApiService";
import { Logo } from "../../types/logo";

const SmartwatchInsert = () => {

    const navigate = useNavigate();
    const { id } = useParams<{ id: string }>();
    const [logos, setLogos] = useState<Option[]>([]);
    const [isChecked, setIsChecked] = useState<boolean>(true);
    const [selectedLogo, setSelectedLogo] = useState<string>("");
    const [name, setName] = useState("");
    const [errors, setErrors] = useState<{ name?: string; selectedLogo?: string }>({});

    useEffect(() => {
        const fetchLogo = async () => {
            const result = await apiService({ url: "/listLogos?type=1" });
            const data: Logo[] = [];
            setLogos([]);
            if (result) {
                data.push(...result);
                for (let i = 0; i < data.length; i++) {
                    setLogos((prev) => [
                        ...prev,
                        {
                            value: data[i].id.toString(),
                            label: data[i].name,
                            image: data[i].data,
                        },
                    ]);
                }
            }
        };

        fetchLogo();
    }, []);

    useEffect(() => {
        if (id) {
            const fetchCarrier = async () => {
                const result = await apiService({ url: `/getSmartWatch/${id}` });
                if (result) {
                    setName(result.name);
                    setSelectedLogo(result.logoId.toString());
                    setIsChecked(result.status === 0 ? true : false);
                }
            };

            fetchCarrier();
        }
    }, []);

    const onSubmit = async (e: React.FormEvent) => {
        e.preventDefault();
        if (!validateForm()) return;

        const data: any = {
            "name": name,
            "logoId": Number.parseInt(selectedLogo),
            "status": isChecked ? 0 : 1
        };
        if (id) {
            data["id"] = Number.parseInt(id);
        }
        const result = await apiService({ method: id ? "PUT" : "POST", url: "/saveSmartWatch", data: data });
        if (result) navigate(-1);
    };

    const onBack = (e: React.FormEvent) => {
        e.preventDefault();
        navigate(-1);
    };


    const validateForm = () => {
        const newErrors: { name?: string; selectedLogo?: string } = {};

        if (!name.trim()) {
            newErrors.name = "Tên không được để trống!";
        }

        if (!selectedLogo) {
            newErrors.selectedLogo = "Vui lòng chọn logo!";
        }

        setErrors(newErrors);
        return Object.keys(newErrors).length === 0;
    };

    return (
        <>
            <Breadcrumb pageName="SmartWatch Form" />

            <div className="flex flex-col">
                {/* <!-- Contact Form --> */}
                <div className="rounded-sm border border-stroke bg-white shadow-default dark:border-strokedark dark:bg-boxdark">
                    <div className="border-b border-stroke py-4 px-6.5 dark:border-strokedark">
                        <h3 className="font-medium text-black dark:text-white">
                            Insert Form
                        </h3>
                    </div>
                    <form onSubmit={onSubmit}>
                        <div className="p-6.5">
                            <div className="mb-4.5">
                                <SelectGroup
                                    defaultValue={selectedLogo}
                                    title="Brand"
                                    options={logos}
                                    onChange={(value) => {
                                        setSelectedLogo(value);
                                        if (errors.selectedLogo) {
                                            setErrors((prev) => ({ ...prev, selectedLogo: undefined }));
                                        }
                                    }}
                                />
                                {errors.selectedLogo && <p className="text-red-500 text-sm mt-1">{errors.selectedLogo}</p>}

                            </div>

                            <div className="mb-4.5">
                                <label className="mb-2.5 block text-black dark:text-white">
                                    Name
                                </label>
                                <input
                                    value={name}
                                    onChange={(e) => {
                                        setName(e.target.value);
                                        if (errors.name) {
                                            setErrors((prev) => ({ ...prev, name: undefined }));
                                        }
                                    }}
                                    type="text"
                                    placeholder="Select subject"
                                    className="w-full rounded border-[1.5px] border-stroke bg-transparent py-3 px-5 text-black outline-none transition focus:border-primary active:border-primary disabled:cursor-default disabled:bg-whiter dark:border-form-strokedark dark:bg-form-input dark:text-white dark:focus:border-primary"
                                />
                                {errors.name && <p className="text-red-500 text-sm mt-1">{errors.name}</p>}

                            </div>
                            <div className="mb-8 flex">
                                <label className="mr-4 block text-black dark:text-white">
                                    Status
                                </label>
                                <div className='mr-4'>
                                    <CheckboxFive
                                        checked={isChecked}
                                        onChange={() => {
                                            if (!isChecked) {
                                                setIsChecked(true);
                                            }
                                        }}
                                        title="Active"
                                    />
                                </div>
                                <div>
                                    <CheckboxFive
                                        checked={!isChecked}
                                        onChange={() => {
                                            if (isChecked) {
                                                setIsChecked(false);
                                            }
                                        }}
                                        title="Inactive"
                                    />
                                </div>
                            </div>

                            <div className="flex gap-4">
                                <button type="button" onClick={onBack} className="flex w-full justify-center items-center rounded-md border border-primary p-3 font-medium text-primary hover:bg-opacity-90">
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
        </>
    );
};

export default SmartwatchInsert;
