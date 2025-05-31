import java.util.*;

public class CurrencyArbitrageDetector {

    static class Edge {
        String from;
        String to;
        double weight;

        Edge(String from, String to, double rate) {
            this.from = from;
            this.to = to;
            this.weight = -Math.log(rate); // log-transform
        }
    }

    public static boolean detectArbitrage(List<String[]> exchangeRates) {
        Set<String> currencies = new HashSet<>();
        List<Edge> edges = new ArrayList<>();

        // Build graph with -log(rate) as weights
        for (String[] rateInfo : exchangeRates) {
            String from = rateInfo[0];
            String to = rateInfo[1];
            double rate = Double.parseDouble(rateInfo[2]);

            currencies.add(from);
            currencies.add(to);
            edges.add(new Edge(from, to, rate));
        }

        // Assign an index to each currency
        List<String> currencyList = new ArrayList<>(currencies);
        Map<String, Integer> indexMap = new HashMap<>();
        for (int i = 0; i < currencyList.size(); i++) {
            indexMap.put(currencyList.get(i), i);
        }

        int n = currencyList.size();
        double[] dist = new double[n];
        Arrays.fill(dist, Double.POSITIVE_INFINITY);
        dist[0] = 0; // Start from any node

        // Relax edges (n - 1) times
        for (int i = 0; i < n - 1; i++) {
            for (Edge edge : edges) {
                int u = indexMap.get(edge.from);
                int v = indexMap.get(edge.to);
                if (dist[u] + edge.weight < dist[v]) {
                    dist[v] = dist[u] + edge.weight;
                }
            }
        }

        // Check for negative cycle
        for (Edge edge : edges) {
            int u = indexMap.get(edge.from);
            int v = indexMap.get(edge.to);
            if (dist[u] + edge.weight < dist[v]) {
                return true; // Arbitrage opportunity detected
            }
        }

        return false; // No arbitrage
    }

    public static void main(String[] args) {
        List<String[]> exchangeRates = Arrays.asList(
            new String[]{"USD", "EUR", "0.9"},
            new String[]{"EUR", "GBP", "0.8"},
            new String[]{"GBP", "USD", "1.6"}
        );

        boolean result = detectArbitrage(exchangeRates);
        System.out.println("Arbitrage Opportunity Exists: " + result); // Should print true
    }
}
