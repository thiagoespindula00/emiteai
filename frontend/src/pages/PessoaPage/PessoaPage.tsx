import Pessoa from "../../types/Pessoa";
import {useEffect, useState} from "react";
import api from "../../api/api";
import {
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

const PessoaPage: React.FC = () => {
    const [pessoas, setPessoas] = useState<Pessoa[]>([])

    useEffect(() => {
        carregaPessoas()
    }, []);

    const carregaPessoas = async () => {
        const { data } = await api.get("/pessoas")
        setPessoas(data.content)
    }

    return (
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
                    <TableRow>
                        <TableCell>{pessoa.cpf}</TableCell>
                        <TableCell>{pessoa.nome}</TableCell>
                        <TableCell>{pessoa.endereco.cep}</TableCell>
                        <TableCell>{pessoa.endereco.bairro}</TableCell>
                        <TableCell>{pessoa.endereco.municipio}</TableCell>
                        <TableCell>{pessoa.endereco.numero}</TableCell>
                        <TableCell>{pessoa.endereco.complemento}</TableCell>
                        <TableCell>{pessoa.endereco.estado}</TableCell>
                        <TableCell align="center">
                            <Tooltip title="Editar">
                                <IconButton color="secondary" size="small">
                                    <EditIcon />
                                </IconButton>
                            </Tooltip>
                            <Tooltip title="Deletar">
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
    )
}

export default PessoaPage