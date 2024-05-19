import SwiftUI
import Shared

struct ContentView: View {
    let characters = [
        CharacterModel(id: "1", name: "Rick", status: "Alive", species: "Human", type: "", gender: "Male", image: ""),
        CharacterModel(id: "2", name: "Morty", status: "Alive", species: "Human", type: "", gender: "Male", image: ""),
        CharacterModel(id: "3", name: "Summer", status: "Alive", species: "Human", type: "", gender: "Woman", image: ""),
        CharacterModel(id: "4", name: "Beth", status: "Alive", species: "Human", type: "", gender: "Woman", image: ""),
    ]
    
    var body: some View {
        ScrollView {
            LazyVGrid(columns: [GridItem(), GridItem()], content: {
                ForEach(characters, id: \.id) { character in
                    CharacterView(character: character)
                }
            })
        }
    }
}

struct CharacterView: View {
    let character: CharacterModel
    
    var body: some View {
        VStack {
            Image("Rick")
                .resizable()
                .aspectRatio(contentMode: .fit)
            Text(character.name)
                .font(.system(size: 30))
            CharacterDescriptionValue(description: "status: ", value: character.status)
            CharacterDescriptionValue(description: "species: ", value: character.species)
            CharacterDescriptionValue(description: "type: ", value: character.type)
            CharacterDescriptionValue(description: "gender: ", value: character.gender)
        }
        .background(Color.gray.opacity(0.3))
        .cornerRadius(16)
    }
    
    init(character: CharacterModel) {
        self.character = character
    }
}

struct CharacterDescriptionValue: View {
    let description: String
    let value: String
    
    var body: some View {
        if (!value.isEmpty) {
            HStack() {
                Text(description)
                    .frame(maxWidth: /*@START_MENU_TOKEN@*/.infinity/*@END_MENU_TOKEN@*/)
                Text(value)
                    .frame(maxWidth: /*@START_MENU_TOKEN@*/.infinity/*@END_MENU_TOKEN@*/)
            }
        }
    }
    
    init(description: String, value: String) {
        self.description = description
        self.value = value
    }
}

struct ContentView_Previews: PreviewProvider {
    static var previews: some View {
        ContentView()
    }
}
