import http from 'k6/http';
import { sleep } from 'k6';

const MATRICULACION_URL =
    __ENV.MATRICULACION_URL || 'http://localhost:8083';

export const options = {
    vus: 100,
    duration: '1m',
};

export default function () {

    http.get(
        `${MATRICULACION_URL}/api/matriculas/listarMatriculas`
    );

    sleep(1);
}