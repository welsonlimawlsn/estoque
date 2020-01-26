import { Cliente } from './cliente';

export interface LoginClienteResposta {
  token: string;
  expiracao: Date;
  cliente: Cliente;
}
