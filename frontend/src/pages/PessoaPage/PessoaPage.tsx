import Pessoa from "../../types/Pessoa";
import {useEffect, useState} from "react";
import api from "../../api/api";
import {
    Button,
    IconButton,
    Paper,
    Table,
    TableBody,
    TableCell,
    TableContainer,
    TableHead,
    TableRow,
    Tooltip
} from "@mui/material";
import DeleteIcon from "@mui/icons-material/Delete";
import EditIcon from "@mui/icons-material/Edit";
import ModalPessoa from "../../components/ModalPessoa";
import {toast} from "react-toastify";

const PessoaPage: React.FC = () => {
    const [pessoas, setPessoas] = useState<Pessoa[]>([])
    const [open, setOpen] = useState(false)
    const [pessoaModal, setPessoaModal] = useState<Pessoa | null>(null)

    useEffect(() => {
        carregaPessoas()
    }, []);

    const carregaPessoas = async () => {
        const { data } = await api.get("/pessoas")
        setPessoas(data.content)
    }

    const abreModal = () => {
        setOpen(true)
    }

    const fechaModal = () => {
        setOpen(false)
        carregaPessoas();
    }

    const cadastrarPessoa = () => {
        setPessoaModal(null)
        abreModal()
    }

    const editarPessoa = (pessoa: Pessoa) => {
        setPessoaModal(pessoa)
        abreModal()
    }

    const deletarPessoa = async (id: number) => {
        try {
            const resposta = await api.delete(`/pessoas/${id}`);
            toast.success("Pessoa excluida")
            carregaPessoas()
        }
        catch (erro: any) {
            toast.error(erro.response.data.mensagem)
        }
    }

    const gerarcsv = async () => {
        try {
            const resposta = await api.post("/pessoas/csv")
            toast.success("CSV está sendo gerado")
        }
        catch (erro: any) {
            toast.error(erro.response.data.mensagem)
        }

    }

    return (
        <>
            <Button onClick={cadastrarPessoa} variant="contained">Cadastrar Pessoa</Button>
            <Button style={{marginLeft: 15}} onClick={gerarcsv} variant="contained">Gerar csv</Button>
            <ModalPessoa open={open} onClose={fechaModal} pessoaEditar={pessoaModal}/>
        <TableContainer component={Paper}>
            <Table>
                <TableHead>
                    <TableRow>
                        <TableCell>CPF</TableCell>
                        <TableCell>Nome</TableCell>
                        <TableCell>CEP</TableCell>
                        <TableCell>Bairro</TableCell>
                        <TableCell>Municipio</TableCell>
                        <TableCell>Número</TableCell>
                        <TableCell>Complemento</TableCell>
                        <TableCell>Estado</TableCell>
                        <TableCell align="center">Ações</TableCell>
                    </TableRow>
                </TableHead>
                <TableBody>
                    {pessoas.map(pessoa =>
                    <TableRow key={pessoa.id}>
                        <TableCell>{pessoa.cpf}</TableCell>
                        <TableCell>{pessoa.nome}</TableCell>
                        <TableCell>{pessoa.endereco.cep}</TableCell>
                        <TableCell>{pessoa.endereco.bairro}</TableCell>
                        <TableCell>{pessoa.endereco.municipio}</TableCell>
                        <TableCell>{pessoa.endereco.numero}</TableCell>
                        <TableCell>{pessoa.endereco.complemento}</TableCell>
                        <TableCell>{pessoa.endereco.estado}</TableCell>
                        <TableCell align="center">
                            <Tooltip title="Editar" onClick={() => {editarPessoa(pessoa)}}>
                                <IconButton color="secondary" size="small">
                                    <EditIcon />
                                </IconButton>
                            </Tooltip>
                            <Tooltip title="Deletar" onClick={() => {deletarPessoa(pessoa.id)}}>
                                <IconButton color="error" size="small">
                                    <DeleteIcon />
                                </IconButton>
                            </Tooltip>
                        </TableCell>
                    </TableRow>
                    )}
                </TableBody>
            </Table>
        </TableContainer>
        </>
    )
}

export default PessoaPage