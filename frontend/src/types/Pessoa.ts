import Endereco from "./Endereco";

export default interface Pessoa {
    id: number,
    nome: string,
    cpf: string
    endereco: Endereco
}
