export class HttpUtilService {
  realizaDownload(data, nomeArquivo = 'arquivo', extensao = '') {
    let url = URL.createObjectURL(data);

    let link = HttpUtilService.createLink(url, nomeArquivo, extensao);

    document.body.appendChild(link);

    link.click();

    document.body.removeChild(link);
  }

  private static createLink(url: string, nomeArquivo: string, extensao: string) {
    let link = document.createElement('a');
    link.setAttribute('href', url);
    link.setAttribute('download', nomeArquivo + extensao);
    link.style.visibility = 'hidden';
    return link;
  }
}
