
package bean;


public class TreinamentoBEAN {
    
    public int numAmostras;
    public int raio;
    public int vizinhos;
    public int eixo_x;
    public int eixo_y;
    public int limiar;
    public String classificador;
    public String camera;

    public String getCamera() {
        return camera;
    }

    public void setCamera(String camera) {
        this.camera = camera;
    }

    

    public String getClassificador() {
        return classificador;
    }

    public void setClassificador(String classificador) {
        this.classificador = classificador;
    }
    
    public int getNumAmostras() {
        return numAmostras;
    }

    public void setNumAmostras(int numAmostras) {
        this.numAmostras = numAmostras;
    }

    public int getRaio() {
        return raio;
    }

    public void setRaio(int raio) {
        this.raio = raio;
    }

    public int getVizinhos() {
        return vizinhos;
    }

    public void setVizinhos(int vizinhos) {
        this.vizinhos = vizinhos;
    }

    public int getEixo_x() {
        return eixo_x;
    }

    public void setEixo_x(int eixo_x) {
        this.eixo_x = eixo_x;
    }

    public int getEixo_y() {
        return eixo_y;
    }

    public void setEixo_y(int eixo_y) {
        this.eixo_y = eixo_y;
    }

    public int getLimiar() {
        return limiar;
    }

    public void setLimiar(int limiar) {
        this.limiar = limiar;
    }

    
    
}
