package com.findork.security.feature.exchange;

import com.findork.security.feature.stock.Stock;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Entity
@Table(name = "exchange")
public class Exchange {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @OneToMany(fetch = FetchType.LAZY,
            mappedBy = "exchange", cascade = CascadeType.ALL)
    private List<Stock> stocks;

    public Exchange(String name) {
        this.name = name;
    }
}
