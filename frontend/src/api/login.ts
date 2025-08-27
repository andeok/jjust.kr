import {API} from '@/api/API.ts'

type LoginRequest = {
    email: string,
    password: string,
}

export async function login({email, password}: LoginRequest): Promise<void> {
    const response = await API.post('/v1/auth/login', {
        email,
        password,
    })
    return response.data
}

export async function checkLogin(): Promise<boolean> {
    try {
        await API.get("/v1/members/me");
        return true // 401이면 false
    } catch {
        return false;
    }
}