import { Navigate } from 'react-router-dom';

const PrivateRoute = ({ children }: { children: JSX.Element }) => {
    const token = sessionStorage.getItem('wdf-token');

    if (!token) {
        return <Navigate to="/auth/signin" replace />;
    }

    return children;
};

export default PrivateRoute;
