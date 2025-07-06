import Pessoa from "../types/Pessoa";

export interface ModalPessoaProps {
    open: boolean,
    onClose: () => void,
    pessoaEditar: Pessoa | null
}