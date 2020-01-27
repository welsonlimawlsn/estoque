import { Cliente } from './cliente';
import { Menu } from '../../menu/model/menu';

export interface LoginClienteResposta {
  token: string;
  expiracao: Date;
  cliente: Cliente;
  menu: Menu[];
}
