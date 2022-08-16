import { EnumSituacaoCadastro } from '../enums/enum-situacao-cadastro';

export interface FornecedorModel {
  id: string;
  nomeFantasia: string;
  razaoSocial: string;
  cnpj: string;
  fone: string;
  email: string;
  situacaoCadastro: EnumSituacaoCadastro;
}
