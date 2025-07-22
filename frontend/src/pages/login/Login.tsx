import {LoginForm} from "@/components/login-form.tsx";
import {login} from "@/api/login.ts";
import {useState} from "react";

export default function Login() {

  const [error, setError] = useState<string | null>(null);

  const handleLogin = async (email: string, password: string) => {
    try {
      const data = await login({email, password});
      // 로그인 성공 시 처리
      setError(null);
    } catch (err: any) {
      setError("로그인 실패");
    }
  };


  return (
      <div className="flex min-h-svh w-full items-center justify-center p-6 md:p-10">
        <div className="w-full max-w-sm">
          <LoginForm onSubmit={handleLogin}/>
        </div>
      </div>
  )

}