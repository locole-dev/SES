import { useEffect, useState } from "react";
import Breadcrumb from "../../components/Breadcrumbs/Breadcrumb";
import { useNavigate, useParams } from "react-router-dom";
import apiService from "../../service/ApiService";
import { CarrierMap } from "../../types/carrier-map";
import { Paint } from "../../components/Icons";
import { API_URL } from "../../util/Constants";

const CarrierMaps = () => {

    const navigate = useNavigate();

    const [data, setData] = useState<CarrierMap[]>([]);
    const { id } = useParams<{ id: string }>();

    const onConfig = async (e: React.FormEvent, mapId: number) => {
        e.preventDefault();
        navigate(`/pages/carriers/${id}/config/${mapId}`);
    };

    const onBack = (e: React.FormEvent) => {
        e.preventDefault();
        navigate(-1);
    };

    useEffect(() => {
        if (id) {
            const fetchCarrier = async () => {
                const result = await apiService({ url: `/listCarrierMappingsByCarrier/${id}` });
                if (result) setData([...result, { id: -9999 }]);
            };

            fetchCarrier();
        }
    }, []);

    return (
        <>
            <Breadcrumb pageName="Carriers Details" />

            <div className="flex flex-col">
                <div className="rounded-md border border-stroke bg-white shadow-default dark:border-strokedark dark:bg-boxdark">
                    <div className="flex border-b border-stroke py-4 px-6.5 dark:border-strokedark">
                        <button className="mr-2" onClick={onBack}>back</button>
                        <h3 className="font-medium text-black dark:text-white">
                            List of Smart Watches
                        </h3>
                    </div>
                    <div className="p-6.5">
                        <div className="grid gap-4 grid-cols-2 sm:grid-cols-3 md:grid-cols-4 lg:grid-cols-4 xl:grid-cols-6 2xl:grid-cols-8">
                            {data.map((packageItem, key) => (
                                <div key={key}>
                                    <button onClick={(e) => {
                                        if (packageItem.id === -9999) {
                                            navigate(`/pages/carriers/${id}/config`);
                                            return;
                                        } else {
                                            onConfig(e, packageItem.id);
                                        }

                                    }}
                                        className="flex flex-col overflow-hidden justify-center items-center relative border rounded-md drop-shadow-md bg-white p-4 hover:bg-slate-50 dark:hover:bg-meta-3 dark:bg-meta-4 max-w-34 min-w-34 min-h-36 h-full">
                                        {packageItem.logoWatchUrl && <div className="flex-1 flex items-center justify-center">
                                            <div className="flex-none"><img src={API_URL + '/' + packageItem.logoWatchUrl} className="w-full h-auto max-h-[6vh] object-contain" /></div>
                                        </div>}

                                        {packageItem.id === -9999 && <div className="absolute">+</div>}
                                        {packageItem.id != -9999 &&
                                            <div>
                                                <div className="flex-none text-black font-medium text-sm py-1 dark:text-white">{packageItem.smartWatchName}</div>
                                                <div className="flex-1 overflow-hidden text-ellipsis text-xs">
                                                    {packageItem.description}
                                                </div>
                                                <div className={`flex-1 overflow-hidden text-ellipsis text-xs py-1 ${packageItem.status === 0 ? 'text-green-500' : 'text-red-500'}`}>
                                                    {packageItem.status === 0 ? 'Active' : 'Inactive'}
                                                </div>
                                                <div className="absolute top-2 right-2">
                                                    <Paint />
                                                </div>
                                            </div>
                                        }
                                    </button>
                                </div>
                            ))}
                        </div>
                    </div>
                </div>
            </div>
        </>
    );
};

export default CarrierMaps;
