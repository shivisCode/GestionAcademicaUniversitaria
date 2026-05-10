import http from 'k6/http';
import { sleep } from 'k6';

const ESTUDIANTE_URL =
    __ENV.ESTUDIANTE_URL || 'http://localhost:8081';

export const options = {
    vus: 100,
    duration: '1m',
};

export default function () {

    http.get(
        `${ESTUDIANTE_URL}/api/estudiantes/listarEstudiantes`
    );

    sleep(1);
}