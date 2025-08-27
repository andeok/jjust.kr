import {BrowserRouter, Routes, Route} from 'react-router-dom';
import {MainLayout} from './pages/MainLayout';
import {LoginPage} from './pages/LoginPage';
// 👆 PrivateRoute import 제거

const appStyles = {
    mobileFrame: {
        width: '100%',
        maxWidth: '450px',
        height: '100vh',
        margin: '0 auto',
        borderLeft: '1px solid #ddd',
        borderRight: '1px solid #ddd',
        display: 'flex',
        flexDirection: 'column' as const,
        backgroundColor: '#f5f5f5',
        position: 'relative' as const,
    },
};

export default function App() {
    return (
        <div style={appStyles.mobileFrame}>
            <BrowserRouter>
                <Routes>
                    <Route path="/login" element={<LoginPage/>}/>
                    <Route path="/*" element={<MainLayout/>}/>
                </Routes>
            </BrowserRouter>
        </div>
    );
}