export type DataTable = {
    title: string;
    showAction?: boolean;
    addAction?: Function;
    detailsAction?: Function;
    editAction?: Function;
    deleteAction?: Function;
    data: any[];
};

export type Columns = {
    title: string;
    styles: string;
};
