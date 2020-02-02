import { PaginacaoRequisicao } from './paginacao-requisicao';

export class ConsultaClientesRequisicao extends PaginacaoRequisicao {
  nomeCliente?: string;
  cpf?: string;
  grupo?: number;
}
