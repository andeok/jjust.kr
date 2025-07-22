import type { LoginRequest } from '@/types/request/LoginRequest.ts'
import { API } from '@/api/API.ts'

export async function login({ email, password }: LoginRequest): Promise<void> {
  const response = await API.post('/v1/auth/login', {
    email,
    password,
  })
  return response.data
}
