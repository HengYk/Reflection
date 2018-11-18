package cn.edu.xidian.ictt.yk.reflection.p4;

/**
 * Created by heart_sunny on 2018/11/16
 * <p>
 * 静态代理
 */
interface ClothFactory {
    void produceCloth();
}

// 代理类
class NikeClothFactory implements ClothFactory {

    @Override
    public void produceCloth() {
        System.out.println("NikeClothFactory");
    }
}

// 被代理类
class NikeProxyFactory implements ClothFactory {

    private NikeClothFactory ncf;

    public NikeProxyFactory(NikeClothFactory ncf) {
        this.ncf = ncf;
    }

    @Override
    public void produceCloth() {
        System.out.println("Receive proxy of Nike");
        ncf.produceCloth();
    }
}

public class TestClothProduct {

    public static void main(String[] args) {
        NikeClothFactory ncf = new NikeClothFactory();
        NikeProxyFactory npf = new NikeProxyFactory(ncf);
        npf.produceCloth();
    }
}
