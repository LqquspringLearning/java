package com.cultivation.javaBasicExtended.posMachine;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.platform.commons.util.StringUtils;

import java.io.IOException;
import java.io.Reader;
import java.util.*;
import java.util.stream.Collectors;

@SuppressWarnings({"WeakerAccess", "unused", "RedundantThrows"})
public class PosMachine {
    private List<Product> productList = new ArrayList<>();

    public void readDataSource(Reader reader) throws IOException {
        // TODO: please implement the following method to pass the test
        // <--start
        if (reader == null) throw new IllegalArgumentException();
        StringBuilder builder = new StringBuilder();
        int readerResult ;
        while ((readerResult = reader.read()) != -1) {

            builder.append((char) readerResult);
        }
        String resultString = builder.toString();
        ObjectMapper mapper = new ObjectMapper();
        productList = mapper.readValue(resultString, new TypeReference<List<Product>>() {
        });
        //productList = gson.fromJson(resultString, productList.getClass());
        // --end-->
    }

    public String printReceipt(String barcodeContent) throws IOException {
        // TODO: please implement the following method to pass the test
        // <--start
        if(productList.size()==0) throw new IllegalStateException();
        String line = System.lineSeparator();
        String receiptHeader = "Receipts" + line;

        String receiptBodyFormat = "%-32s%-11d%d" + line;
        String receiptFooter = "Price: %d" + line;
        String receiptSplitline = "------------------------------------------------------------" + line;
        if (StringUtils.isBlank(barcodeContent) || barcodeContent.equals("[]")) {
            return receiptHeader +
                    receiptSplitline +
                    receiptSplitline +
                    String.format(receiptFooter, 0);
        }
        List<List<Product>> needToPayItems = new ArrayList<>();
        List<String> barCodes = new ObjectMapper().readValue(barcodeContent, new TypeReference<List<String>>() {
        });

        barCodes.forEach(code -> {
            List<Product> products = productList.stream().filter(p -> p.getId().equals(code)).collect(Collectors.toList());
            boolean isFound = false;
            for (List<Product> items : needToPayItems) {
                if (items.get(0).equals(products.get(0))) {
                    items.add(products.get(0));
                    isFound = true;
                }
            }
            if (!isFound) {
                needToPayItems.add(products);
            }
        });
        needToPayItems.stream().distinct();

        StringBuilder receiptBody = new StringBuilder();
        needToPayItems.forEach(items -> receiptBody.append(String.format(receiptBodyFormat, items.get(0).getName(), items.get(0).getPrice(), items.size())));

        return receiptHeader +
                receiptSplitline +
                receiptBody +
                receiptSplitline +
                String.format(receiptFooter, needToPayItems.stream().mapToInt(items -> items.stream().mapToInt(Product::getPrice).sum()).sum());
        // --end-->
    }
}

@SuppressWarnings("unused")
class Product {
    private String id;
    private String name;
    private Integer price;

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Integer getPrice() {
        return price;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) return false;
        if (this == obj) return true;
        if (getClass() != obj.getClass()) return false;

        Product other = (Product) obj;

        return Objects.equals(id, other.id);
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }
}