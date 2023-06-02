package UnitTests.Model;

import Model.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;
import java.util.ArrayList;

import static org.mockito.Mockito.*;

public class StorageTest {
    private int[] pointsX = {0,0};
    private int[] pointsY = {0,0};
    private Storage storage;

    private ArrayList<Material> materials;

    @BeforeEach
    public void setup(){
        materials = new ArrayList<Material>();
        storage=new Storage(materials,pointsX,pointsY,1);
    }

    @Test
    public void Test_GetCollectable(){
        Storage spyStorage = spy(storage);

        spyStorage.GetCollectable();

        verify(spyStorage).Collect();
    }

    @Test
    public void Test_SetCollectable_WhenMaterialsIsNull(){
        storage=new Storage(null,pointsX,pointsY,1);
        try {
            Material mMock = mock(Material.class);
            storage.setCollectable(mMock);

            Field fMaterials = Storage.class.getDeclaredField("materials");

            fMaterials.setAccessible(true);

            ArrayList<Material> fMaterialsValue = (ArrayList<Material>) fMaterials.get(storage);

            Assertions.assertNotNull(fMaterialsValue);
            Assertions.assertEquals(1,fMaterialsValue.size());


        } catch (NoSuchFieldException | IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void Test_SetCollectable_WhenMaterialsIsNotNull(){
        try {
            Material mMock = mock(Material.class);
            storage.setCollectable(mMock);

            Field fMaterials = Storage.class.getDeclaredField("materials");

            fMaterials.setAccessible(true);

            ArrayList<Material> fMaterialsValue = (ArrayList<Material>) fMaterials.get(storage);

            Assertions.assertEquals(1,fMaterialsValue.size());


        } catch (NoSuchFieldException | IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void Test_Collect_WhenMaterialsSizeIsZero(){
        Assertions.assertNull(storage.Collect());
    }

    @Test
    public void Test_Collect_WhenMaterialsIsNotZero(){
        try {
            Material mMock = mock(Material.class);
            storage.setCollectable(mMock);

            Field fMaterials = Storage.class.getDeclaredField("materials");

            fMaterials.setAccessible(true);

            Material material = storage.Collect();
            Assertions.assertEquals(mMock,material);

            ArrayList<Material> fMaterialsValue = (ArrayList<Material>) fMaterials.get(storage);

            Assertions.assertFalse(fMaterialsValue.contains(mMock));
            Assertions.assertEquals(0,fMaterialsValue.size());


        } catch (NoSuchFieldException | IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void Test_DestroyMaterial(){
        try {
            Material mMock1 = mock(Material.class);
            Material mMock2 = mock(Material.class);
            storage.setCollectable(mMock1);
            storage.setCollectable(mMock2);

            Field fMaterials = Storage.class.getDeclaredField("materials");

            fMaterials.setAccessible(true);

            storage.DestroyMaterial();

            ArrayList<Material> fMaterialsValue = (ArrayList<Material>) fMaterials.get(storage);

            Assertions.assertFalse(fMaterialsValue.contains(mMock1));
            Assertions.assertFalse(fMaterialsValue.contains(mMock2));
            Assertions.assertEquals(0,fMaterialsValue.size());


        } catch (NoSuchFieldException | IllegalAccessException e) {
            e.printStackTrace();
        }
    }
}
