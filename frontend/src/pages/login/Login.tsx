import {LoginForm} from '@/components/login-form.tsx'
import {login} from '@/api/login.ts'

export default function Login() {
  const handleLogin = async (email: string, password: string) => {
    try {
      await login({email, password})
      // 로그인 성공 시 처리
    } catch (error) {
      console.log(error)
    }
  }

  return (
      <>
        <div className='flex min-h-svh w-full items-center justify-center p-6 md:p-10'>
          <div className='w-full max-w-sm'>
            <LoginForm onSubmit={handleLogin}/>
          </div>
        </div>
      </>
  )
}
