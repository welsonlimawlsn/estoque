export class PaginacaoResposta<T> {
  lista: T[];
  paginaAtual: number;
  totalPaginas: number;
  quantidadePorPagina: number;
}
