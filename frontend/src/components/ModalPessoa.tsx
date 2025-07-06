import {ModalPessoaProps} from "./ModalPessoaProps";
import {useEffect, useState} from "react";
import Pessoa from "../types/Pessoa";
import {Button, Modal, TextField, Typography} from "@mui/material";
import {StyledBox, StyledContent, StyledFooter, StyledHeader} from "./ModalPessoa.styles";
import {toast, ToastContainer} from "react-toastify";
import api from "../api/api";

const vazio: Pessoa = {
    id: 0,
    nome: '',
    cpf: '',
    endereco: {
        numero: '',
        complemento: '',
        cep: '',
        bairro: '',
        municipio: '',
        estado: '',
    },
};

const ModalPessoa: React.FC<ModalPessoaProps> = ({open, onClose, pessoaEditar}) => {
    const [pessoaForm, setPessoaForm] = useState<Pessoa>(vazio)

    useEffect(() => {
        if (open) {
            setPessoaForm(pessoaEditar ?? vazio)
        }
    }, [pessoaEditar, open]);

    const salvarPessoa = async () => {
        try {
            if (pessoaEditar == null) {
                const resposta = await api.post("/pessoas", pessoaForm)
                toast.success("Pessoa cadastrada")
            }
            else {
                const resposta = await api.put(`/pessoas/${pessoaForm.id}`, pessoaForm)
                toast.success("Pessoa alterada")
            }

            onClose()
        } catch (erro: any) {
            toast.error(erro.response.data.mensagem)
        }
    }

    return (
        <>
            <Modal open={open} onClose={onClose}>
                <StyledBox>
                    <StyledHeader>
                        <Typography variant="h4" mb={2}>Cadastrar Pessoa</Typography>
                    </StyledHeader>
                    <StyledContent>
                        <TextField
                            label="CPF"
                            value={pessoaForm.cpf}
                            onChange={e => setPessoaForm(prevState => ({
                                ...prevState, cpf: e.target.value
                            }))}/>
                        <TextField
                            label="Nome"
                            value={pessoaForm.nome}
                            onChange={e => setPessoaForm(prevState => ({
                                ...prevState, nome: e.target.value
                            }))}/>
                        <TextField
                            label="CEP"
                            value={pessoaForm.endereco.cep}
                            onChange={e => setPessoaForm(prevState => ({
                                ...prevState, endereco: {...prevState.endereco, cep: e.target.value},
                            }))}/>
                        <TextField
                            label="Municipio"
                            value={pessoaForm.endereco.municipio}
                            onChange={e => setPessoaForm(prevState => ({
                                ...prevState, endereco: {...prevState.endereco, municipio: e.target.value},
                            }))}/>
                        <TextField
                            label="Bairro"
                            value={pessoaForm.endereco.bairro}
                            onChange={e => setPessoaForm(prevState => ({
                                ...prevState, endereco: {...prevState.endereco, bairro: e.target.value},
                            }))}/>
                        <TextField
                            label="Estado"
                            value={pessoaForm.endereco.estado}
                            onChange={e => setPessoaForm(prevState => ({
                                ...prevState, endereco: {...prevState.endereco, estado: e.target.value},
                            }))}/>
                        <TextField
                            label="Numero"
                            value={pessoaForm.endereco.numero}
                            onChange={e => setPessoaForm(prevState => ({
                                ...prevState, endereco: {...prevState.endereco, numero: e.target.value},
                            }))}/>
                        <TextField
                            label="Complemento"
                            value={pessoaForm.endereco.complemento}
                            onChange={e => setPessoaForm(prevState => ({
                                ...prevState, endereco: {...prevState.endereco, complemento: e.target.value},
                            }))}/>
                    </StyledContent>
                    <StyledFooter>
                        <Button variant="contained" color="error" onClick={onClose}>Cancelar</Button>
                        <Button variant="contained" onClick={salvarPessoa}>Salvar</Button>
                    </StyledFooter>
                </StyledBox>
            </Modal>
            <ToastContainer/>
        </>
    )
}

export default ModalPessoa;