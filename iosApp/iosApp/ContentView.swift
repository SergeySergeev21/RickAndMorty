import SwiftUI
import Shared

struct ContentView: View {
    let list = [
        CharacterCell(character: CharacterModel(id: "1", image: "img", name: "Rick", status: .alive, species: "Human", lastKnownLocation: "Earth (Replacement Dimension)", firstSeenIn: "Close Rick-counters of the Rick Kind")),
        CharacterCell(character: CharacterModel(id: "2", image: "img", name: "Morty", status: .alive, species: "Human", lastKnownLocation: "Earth (Replacement Dimension)", firstSeenIn: "Close Rick-counters of the Rick Kind")),
        CharacterCell(character: CharacterModel(id: "3", image: "img", name: "Summer", status: .dead, species: "Human", lastKnownLocation: "Earth (Replacement Dimension)", firstSeenIn: "Close Rick-counters of the Rick Kind")),
        CharacterCell(character: CharacterModel(id: "4", image: "img", name: "Beth", status: .unknown, species: "Human", lastKnownLocation: "Earth (Replacement Dimension)", firstSeenIn: "Close Rick-counters of the Rick Kind"))
    ]
    let columns = [
        GridItem(),
        GridItem()
    ]
    
    
    var body: some View {
        ScrollView {
            LazyVGrid(columns: columns, content: {
                ForEach(list, id: \.character.id){ cell in
                    cell
                }
            }
            )
        }
    }
}

struct CharacterCell: View {
    let character: CharacterModel
    
    var body: some View {
        VStack(spacing: 0) {
            Image("rick")
                .resizable()
                .aspectRatio(contentMode: .fit)
            
            Text(character.name)
                .font(.system(size: 40))
            
            VStack(alignment: .leading, spacing: 8) {
                StatusAndSpecies(status: character.status, species: character.species)
                TitleDescription(title: "Last known location:", description: character.lastKnownLocation)
                TitleDescription(title: "First seen in:", description: character.firstSeenIn)
            }
            .frame(maxWidth: .infinity, alignment: .leading)
            .padding(.horizontal, 8)
            .padding(.vertical, 4)
        }
        .background(Color.gray.opacity(0.3))
        .cornerRadius(16)
    }
    
    init(character: CharacterModel) {
        self.character = character
    }
}

struct StatusAndSpecies: View {
    let status: Status
    let species: String
    let color: Color
    
    var body: some View {
        HStack {
            Circle()
                .foregroundColor(color)
                .frame(width: 10)
            Text(status.name.lowercased().capitalized)
            Text("-")
            Text(species)
        }
    }
    
    init(status: Status, species: String) {
        self.status = status
        self.species = species
        self.color = {
            switch status {
            case .alive :
                return .green
                
            case .dead :
                return .red
                
            case .unknown:
                return .gray
            default:
                return .pink
            }
        }()
    }
}

struct TitleDescription: View {
    let title: String
    let description: String
    
    var body: some View {
        VStack(alignment: .leading) {
            Text(title)
                .font(.caption)
            Text(description)
        }
    }
    
    init(title: String, description: String) {
        self.title = title
        self.description = description
    }
}

struct ContentView_Previews: PreviewProvider {
    static var previews: some View {
        ContentView()
    }
}


