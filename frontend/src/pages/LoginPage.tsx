// src/pages/LoginPage.tsx
import React from 'react';
import {useNavigate} from "react-router-dom";
import {login} from '../api/login'

const loginPageStyles = {
    container: { display: 'flex', flexDirection: 'column' as const, justifyContent: 'center', alignItems: 'center', height: '100vh', padding: '20px', backgroundColor: '#fff' },
    title: { fontSize: '28px', fontWeight: 'bold', marginBottom: '32px' },
    input: { width: '100%', maxWidth: '350px', padding: '14px', marginBottom: '16px', border: '1px solid #ccc', borderRadius: '8px', fontSize: '16px' },
    loginButton: { width: '100%', maxWidth: '350px', padding: '14px', border: 'none', borderRadius: '8px', backgroundColor: '#1a73e8', color: 'white', fontSize: '16px', fontWeight: 'bold', cursor: 'pointer' },
    divider: { display: 'flex', alignItems: 'center', color: '#888', margin: '32px 0', width: '100%', maxWidth: '350px' },
    dividerLine: { flex: 1, height: '1px', backgroundColor: '#ccc' },
    dividerText: { margin: '0 16px' },
    socialLoginContainer: { display: 'flex', gap: '16px' },
    socialButton: { width: '50px', height: '50px', borderRadius: '50%', border: '1px solid #ddd', display: 'flex', justifyContent: 'center', alignItems: 'center', cursor: 'pointer', backgroundColor: '#f5f5f5' },
};

interface LoginPageProps {
    onLoginSuccess: () => void;
}

export const LoginPage: React.FC<LoginPageProps> = ({ onLoginSuccess }) => {
    const navigate = useNavigate(); // 👈 [수정] navigate 훅 사용

    const request = {
        email: "test1@naver.com",
        password: "1234",
    }

    const handleLogin = () => {
        // ... 실제 로그인 API 호출 ...
        login(request)
        navigate('/'); // 👈 [수정] 로그인 성공 시 메인 페이지('/')로 이동
    };


    return (
        <div style={loginPageStyles.container}>
            <h1 style={loginPageStyles.title}>로그인</h1>
            <input type="email" placeholder="이메일" style={loginPageStyles.input} />
            <input type="password" placeholder="비밀번호" style={loginPageStyles.input} />
            <button style={loginPageStyles.loginButton} onClick={handleLogin}>
                로그인
            </button>
            <div style={loginPageStyles.divider}>
                <div style={loginPageStyles.dividerLine} />
                <span style={loginPageStyles.dividerText}>또는</span>
                <div style={loginPageStyles.dividerLine} />
            </div>
            <div style={loginPageStyles.socialLoginContainer}>
                {/* TODO: 각 소셜 로그인 기능 구현 */}
                <button style={loginPageStyles.socialButton} title="구글 로그인">G</button>
                <button style={loginPageStyles.socialButton} title="깃허브 로그인">H</button>
                <button style={loginPageStyles.socialButton} title="카카오 로그인">K</button>
            </div>
        </div>
    );
};