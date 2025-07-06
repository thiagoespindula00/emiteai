import React from 'react';
import './App.css';
import {AppBar, Toolbar, Typography} from "@mui/material";
import PessoaPage from "./pages/PessoaPage/PessoaPage";

function App() {
  return (
    <>
      <AppBar>
        <Toolbar>
          <Typography>Cadastro de pessoas físicas</Typography>
        </Toolbar>
      </AppBar>
        <div style={{ padding: '20px', marginTop: '60px' }}>
            <PessoaPage />
        </div>
    </>
  );
}

export default App;
