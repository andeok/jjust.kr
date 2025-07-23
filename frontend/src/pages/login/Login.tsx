import {LoginForm} from '@/components/login-form.tsx'
import {login} from '@/api/login.ts'
import {useNavigate} from "react-router-dom";
import {useState} from "react";

export default function Login() {
  const navigate = useNavigate();
  const [error, setError] = useState<string | null>(null)

  const handleLogin = async (email: string, password: string) => {
    try {
      await login({email, password})
      // 로그인 성공 시 처리
      navigate('/') // 로그인 성공 시 메인화면으로 이동
    } catch (error) {
      console.log(error)
      setError('로그인 실패! 이메일 또는 비밀번호를 확인하세요.')
    }
  }

  return (
      <>
        <div className='flex min-h-svh w-full items-center justify-center p-6 md:p-10'>
          <div className='w-full max-w-sm'>
            <LoginForm onSubmit={handleLogin} error={error}/>
          </div>
        </div>
      </>
  )
}
