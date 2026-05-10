import http from 'k6/http';
import { sleep } from 'k6';

const COMPROBANTE_URL =
    __ENV.COMPROBANTE_URL || 'http://localhost:8084';

export const options = {
    vus: 100,
    duration: '1m',
};

export default function () {

    http.get(
        `${COMPROBANTE_URL}/api/comprobantes/listarComprobantes`
    );

    sleep(1);
}