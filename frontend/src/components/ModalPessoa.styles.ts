import styled from "styled-components";
import {Box} from "@mui/material";

export const StyledBox = styled(Box)`
    position: absolute;
    top: 50%;
    left: 50%;
    transform: translate(-50%, -50%);
    background: white;
    padding: 20px;
    border-radius: 8px;

    display: flex;
    flex-direction: column;
    width: 90%;
    max-width: 800px;
    max-height: 90vh;
`;

export const StyledHeader = styled(Box)`
    display: flex;
    justify-content: center;
    text-align: center;
    margin-bottom: 16px;
    width: 100%;
    position: relative;
`;

export const StyledContent = styled(Box)`
    padding: 10px;
    margin: 10px;
    display: flex;
    flex-direction: column;
    overflow-y: auto;
    gap: 16px;
`;

export const StyledFooter = styled(Box)`
    text-align: center;
    margin-top: 16px;
    display: flex;
    justify-content: end;
    align-items: center;
    gap: 10px;
`;