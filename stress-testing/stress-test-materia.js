import http from 'k6/http';
import { sleep } from 'k6';

const MATERIA_URL =
    __ENV.MATERIA_URL || 'http://localhost:8082';

export const options = {
    vus: 100,
    duration: '1m',
};

export default function () {

    http.get(
        `${MATERIA_URL}/api/materias/listarMaterias`
    );

    sleep(1);
}