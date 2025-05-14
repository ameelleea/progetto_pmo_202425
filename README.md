# progetto_pmo_202425

Simulazione di ristorante
Progetto d'esame per l'insegnamento di Programmazione e Modellazione a Oggetti, a.a. 2024/25

Motivazione e obiettivi:
L'obiettivo di questo progetto è quello di realizzare un'applicazione che simuli il funzionamento di un ristorante. Il locale sarà composto da tre reparti per la prepazione di cibo e bevande (cucina, pizzeria e bar), più cassa e sala. La sala conterrà una serie di tavoli di capacità differenti, suddivisi in ranghi, ciascuno assegnato a un cameriere. Il maitre dovrà gestire i gruppi di clienti in arrivo, assegnando loro il primo tavolo disponibile con un numero sufficiente di posti, e i camerieri dovranno prendere gli ordini e inviarli alla cassa, che li smisterà ai reparti. I reparti dovranno evadere le ordinazioni in ordine di arrivo. La cassa dovrà tenere traccia di quali tavoli della sala sono occupati, delle ordinazioni di ciascun tavolo e calcolare il totale di spesa di ciascuno, oltre a calcolare il guadagno totale della giornata.
 
Funzionalità minimali ritenute obbligatorie:
-I clienti arrivano al ristorante in gruppi da 2 a 10, e rimangono in attesa finchè il maitre non assegna loro un tavolo con un numero sufficiente di posti. Il maitre può vedere quali tavoli sono liberi accedendo alla cassa.
-Una volta che i clienti hanno preso posto, il cameriere del rango in cui si trova il tavolo notifica la cassa che il tavolo è occupato
-I clienti scelgono cosa ordinare dal menu. Ciasun prodotto nel menù ha associato il prezzo, il reparto di competenza e il tempo di preparazione. Ciascun cliente sceglie di ordinare una bevanda e un piatto dalla cucina o dalla pizzeria.
-Il cameriere raccoglie l'ordinazione e la invia alla cassa, che smista ciascun prodotto verso il reparto di competenza. 
-Ciascun reparto ha da 1 a 5 lavoratori. Quando un'ordinazione raggiunge un reparto, viene presa in carico dal lavoratore con il minor carico di lavoro.
-Le ordinazioni vengono preparate dai reparti. Prodotti con tempi di preparazione diversi assegnati a uno stesso tavolo rimangono in attesa che tutti i piatti nell'ordinazione siano pronti, prima di inviarli al tavolo.
-Una volta che i clienti hanno terminato di consumare le loro ordinazioni, scelgono se ordinare della caffetteria e/o un dessert.
-Al termine del pasto, il cameriere richiede il conto del tavolo alla cassa. La cassa emette il conto e chiude il tavolo, rendendolo nuovamente disponibile per il prossimo gruppo di clienti. 
-Al termine di ciascun turno lavorativo, la cassa calcola il totale dei guadagni della giornata, e il totale parziale per ciascun cameriere e reparto, sottraendo dal guadagno lordo gli stipendi del personale. 
 
 
Funzionalità opzionali:
-Implementazione di un sistema di prenotazione dei tavoli
-Implementazione di un sistema di mance per i camerieri, e di conseguenza di una classificazione dei camerieri in base a chi ha ricevuto più mance
-Aggiunta di una lista di ingredienti per ciascun item nel menù, in modo da poter implementare inventario e fornitori e aggiungere alle spese del locale, oltre ai salari dei dipendenti, anche i costi di fornitura.