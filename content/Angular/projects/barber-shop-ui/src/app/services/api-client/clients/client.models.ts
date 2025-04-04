export interface SaveClientRequest {
  name: string
  email: string
  phone: string
}


export interface UpdateClientResponse {
  name: string
  email: string
  phone: string
}

export interface ResponseClientRequest {
  name: string
  email: string
  phone: string
}

export interface SaveClientResponse{
  name: string
  email: string
  phone: string
}

export interface ListClientResponse {
  name: string
  email: string
  phone: string
}

export interface DetailClientResponse{
  id: number
  name: string
  email: string
  phone: string
}
