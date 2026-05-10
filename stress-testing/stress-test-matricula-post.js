import http from 'k6/http';
import { sleep } from 'k6';

const MATRICULACION_URL =
    __ENV.MATRICULACION_URL || 'http://localhost:8083';

export const options = {
    vus: 100,
    duration: '1m',
};

export default function () {

    const payload = JSON.stringify({
        estudianteId: 1,
        materiaId: 1
    });

    const params = {
        headers: {
            'Content-Type': 'application/json',
        },
    };

    http.post(
        `${MATRICULACION_URL}/api/matriculas/registrarMatricula`,
        payload,
        params
    );

    sleep(1);
}